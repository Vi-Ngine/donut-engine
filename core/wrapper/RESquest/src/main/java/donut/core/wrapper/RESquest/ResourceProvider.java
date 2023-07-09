package donut.core.wrapper.RESquest;

import java.util.*;

public class ResourceProvider {
    Map<String, Object> resources = new HashMap<>();
    Set<ResourceRequest> pendingRequests = new HashSet<>();
    public void addResource(Object resource)
    {
        resources.put(resource.getClass().toString(), resource);
        processRequests();
    }

    public void addConsumer(IResourceConsumer consumer)
    {
        pendingRequests.addAll(Arrays.asList(consumer.getRequests()));
        processRequests();
    }

    public void processRequests()
    {
        List<ResourceRequest> processedRequest = new ArrayList<>();
        for(ResourceRequest request : pendingRequests)
        {
            Object requestResource = resources.get(request.resourceClass.toString());
            if(request.consumeCallback.consume(requestResource))
            {
                processedRequest.add(request);
            }
        }

        pendingRequests.removeAll(processedRequest);
    }
}
