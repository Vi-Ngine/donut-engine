package donut.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


public class ApiService {
    @Autowired
    private GameEngine engine;
}
