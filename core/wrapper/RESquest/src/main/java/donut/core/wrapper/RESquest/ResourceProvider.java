package donut.core.wrapper.RESquest;

import java.util.*;

public class ResourceProvider {
    Map<String, Object> resources = new HashMap<>();
    Set<ResourceConsumer> consumers = new HashSet<>();
    public void addResource(Object resource)
    {
        resources.put(resource.getClass().toString(), resource);
    }

    public <T> T getResource(Class<T> resourceClass)
    {
        return (T)resources.get(resourceClass.toString());
    }

    public void addConsumer(IResourceConsumer consumer)
    {
        consumers.add(consumer.getConsumer());
    }

    public void processRequests()
    {
        for(ResourceConsumer consumer : consumers)
        {
            for(ResourceRequest request : consumer.getUnresolvedRequests())
            {
                if(request.consume(getResource(request.resourceClass)))
                {
                    request.setState(ResourceRequest.RequestState.RESOLVED);
                }
            }
        }
    }
}
