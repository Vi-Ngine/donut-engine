import donut.core.wrapper.lwjgl3.LWJGL3;
import org.junit.jupiter.api.Test;

public class LWJGL3Tests {
    @Test
    void test1() throws InterruptedException {
        Thread thread = new Thread(new LWJGL3());
        thread.start();

        thread.join();
    }
}
