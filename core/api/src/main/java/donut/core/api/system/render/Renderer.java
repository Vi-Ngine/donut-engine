package donut.core.api.system.render;

import wrapper.ecsystem.GameComponent;

import java.util.HashSet;
import java.util.Set;

public class Renderer extends GameComponent {
    private final Set<IRenderCallback> renderListeners = new HashSet<>();

    public void addRenderCallback(IRenderCallback renderCallback)
    {
        renderListeners.add(renderCallback);
    }

    public IRenderCallback[] getRenderCallBacks()
    {
        renderListeners.remove(null);
        return renderListeners.toArray(new IRenderCallback[0]);
    }
}
