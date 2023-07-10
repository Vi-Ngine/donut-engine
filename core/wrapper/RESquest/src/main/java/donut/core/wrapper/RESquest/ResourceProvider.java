package donut.core.wrapper.RESquest;

import java.util.*;

public class ResourceProvider {
    Map<String, Object> resources = new HashMap<>();
    Set<ResourceConsumer> consumers = new HashSet<>();
    public void addResource(Object resource, Class<?> scope)
    {
        resources.put(getResourceKey(resource.getClass(), scope), resource);
        processRequests();
    }

    public void addResource(Object resource)
    {
        addResource(resource, Object.class);
    }

    public <T> T getResource(Class<T> resourceClass, Class<?> scope)
    {
        do {
            T resource = (T)resources.get(getResourceKey(resourceClass, scope));
            if(resource != null) return resource;
            scope = scope.getSuperclass();
        } while(scope != null);

        return null;
    }

    private String getResourceKey(Class<?> resourceClass, Class<?> receiverScope)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(resourceClass.getName());

        for(String clazz : getFullClasses(receiverScope))
        {
            builder.append(":");
            builder.append(clazz);
        }

        return builder.toString();
    }

    private String[] getFullClasses(Class<?> receiverScope)
    {
        List<String> absoluteScopePath = new ArrayList<>();

        do {
            absoluteScopePath.add(receiverScope.getName());
            receiverScope = receiverScope.getSuperclass();
        } while(receiverScope != null);

        Collections.reverse(absoluteScopePath);

        return absoluteScopePath.toArray(String[]::new);
    }

    public void addConsumer(IResourceConsumer consumer)
    {
        consumers.add(consumer.getConsumer());
        consumer.getConsumer().setRequestPostListenerInternal(new IOnRequestPostListener() {
            @Override
            public void onRequestPost(ResourceRequest request) {
                processRequest(request);
            }
        });

        processRequests();
    }

    public void processRequests()
    {
        for(ResourceConsumer consumer : consumers)
        {
            for(ResourceRequest request : consumer.getUnresolvedRequests())
            {
                processRequest(request);
            }
        }
    }

    private void processRequest(ResourceRequest request)
    {
        Object resource = getResource(request.resourceClass, request.getScope());
        if(resource == null) return;

        if(request.consume(resource))
        {
            request.setState(ResourceRequest.RequestState.RESOLVED);
        }
    }
}
