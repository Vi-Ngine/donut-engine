import donut.core.wrapper.RESquest.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RESquestTest
{
    @Test
    public void test1()
    {
        TextProvider provider = new TextProvider();
        provider.addResource("hellobitch");

        TextConsumer consumer = new TextConsumer();
        provider.addConsumer(consumer);

        Assertions.assertEquals(0, consumer.getConsumer().getUnresolvedRequests().length);
        Assertions.assertEquals("hellobitch", consumer.consumeString);
    }

    @Test
    public void test2()
    {
        TextProvider provider = new TextProvider();
        provider.addResource("hellobitch");

        TextConsumer2 consumer = new TextConsumer2();
        provider.addConsumer(consumer);

        Assertions.assertTrue(consumer.getConsumer().getUnresolvedRequests().length > 0);
        Assertions.assertEquals("hellobitch", consumer.consumeString);
    }

    @Test
    public void test3()
    {
        TextProvider provider = new TextProvider();
        provider.addResource("hellobitch", TextConsumer.class);

        TextConsumer consumer = new TextConsumer();
        TextConsumer2 consumer2 = new TextConsumer2();

        provider.addConsumer(consumer);
        provider.addConsumer(consumer2);

        Assertions.assertEquals("hellobitch", consumer.consumeString);
        Assertions.assertEquals("default", consumer2.consumeString);
    }

    @Test
    public void test4()
    {
        TextProvider stringProvider = new TextProvider();
        IntProvider intProvider = new IntProvider();


        Consumer consumer = new Consumer();

        stringProvider.addConsumer(consumer);
        intProvider.addConsumer(consumer);

        Assertions.assertEquals("default", consumer.stringResource);
        Assertions.assertEquals(123, consumer.intResource);
    }

    @Test
    public void getFullClassesTest()
    {
        ResourceProvider resourceProvider = new ResourceProvider();

        Method method;
        try {
            method = ResourceProvider.class.getDeclaredMethod("getFullClasses", Class.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        method.setAccessible(true);

        String[] result;
        try {
            result = (String[])method.invoke(resourceProvider, String.class);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("java.lang.Object", result[0]);
        Assertions.assertEquals("java.lang.String", result[1]);
    }

    @Test
    public void getResourceKeyTest()
    {
        ResourceProvider resourceProvider = new ResourceProvider();

        Method method;
        try {
            method = ResourceProvider.class.getDeclaredMethod("getResourceKey", Class.class, Class.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        method.setAccessible(true);

        String result;
        try {
            result = (String)method.invoke(resourceProvider, String.class, this.getClass());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("java.lang.String:java.lang.Object:RESquestTest", result);
    }

    @Test
    public void requestPostListenerTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TextConsumer consumer = new TextConsumer();

        boolean[] shouldBeTrue = {false};

        Method setRequestPostListener =
                ResourceConsumer.class.getDeclaredMethod("setRequestPostListenerInternal", IOnRequestPostListener.class);


        setRequestPostListener.setAccessible(true);
        setRequestPostListener.invoke(consumer.getConsumer(), new IOnRequestPostListener() {
            @Override
            public void onRequestPost(ResourceRequest request) {
                shouldBeTrue[0] = true;
                System.out.println("hello world");
            }
        });

        consumer.getConsumer().postRequest(new ResourceRequest(String.class, new IConsumeCallback() {
            @Override
            public boolean consume(Object resource) {
                return false;
            }
        }));

        Assertions.assertTrue(shouldBeTrue[0]);
    }
}
