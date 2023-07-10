package donut.core.wrapper.RESquest;

public interface IOnRequestPostListener<T> {
    void onRequestPost(ResourceRequest<T>  request);
}
