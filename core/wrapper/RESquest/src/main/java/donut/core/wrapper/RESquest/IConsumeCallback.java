package donut.core.wrapper.RESquest;

public interface IConsumeCallback<T> {
    boolean consume(T resource);
}
