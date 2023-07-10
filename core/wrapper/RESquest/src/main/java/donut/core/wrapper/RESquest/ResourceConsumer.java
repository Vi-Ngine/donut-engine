package donut.core.wrapper.RESquest;

import java.util.HashSet;
import java.util.Set;

public class ResourceConsumer {
    private Set<ResourceRequest> unresolvedRequests = new HashSet<>();

    public ResourceRequest[] getUnresolvedRequests()
    {
        return unresolvedRequests.toArray(ResourceRequest[]::new);
    }

    private IOnRequestPostListener requestPostListener;

    protected void setRequestPostListenerInternal(IOnRequestPostListener requestPostListener)
    {
        this.requestPostListener = requestPostListener;
    }

    public void postRequest(ResourceRequest postingRequest)
    {
        if(postingRequest.getState() == ResourceRequest.RequestState.RESOLVED) return;

        unresolvedRequests.add(postingRequest);
        postingRequest.setRequestResolvedListener(new RunnableOne<ResourceRequest>() {
            @Override
            public void run(ResourceRequest request) {
                unresolvedRequests.remove(request);
            }
        });

        if(requestPostListener != null)
            requestPostListener.onRequestPost(postingRequest);
    }
}
