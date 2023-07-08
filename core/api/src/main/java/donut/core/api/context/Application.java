package donut.core.api.context;

import donut.core.api.GameEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    private static Application current;

    private GameEngine gameEngine;

    private Application(){}

    public GameEngine gameEngine()
    {
        return gameEngine;
    }

    public static Application Current()
    {
        if(current == null) current = new Application();
        return current;
    }
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Current().gameEngine = context.getBean(GameEngine.class);
    }
}
