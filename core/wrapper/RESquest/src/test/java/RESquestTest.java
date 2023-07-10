import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceProvider;
import donut.core.wrapper.RESquest.ResourceRequest;
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

        Assertions.assertTrue(consumer.consumeString.isEmpty());

        provider.processRequests();
        provider.processRequests();

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

        Assertions.assertTrue(consumer.consumeString.isEmpty());

        provider.processRequests();
        provider.processRequests();

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

        provider.processRequests();
        provider.processRequests();

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

        stringProvider.processRequests();
        intProvider.processRequests();

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
}
