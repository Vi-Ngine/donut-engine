package donut.core.api.system.debug;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import donut.core.api.GameEntity;
import donut.core.api.system.render.IRenderCallback;
import donut.core.wrapper.ECSystem.Component;

public class Debugger extends Component implements IRenderCallback {
    public static ComponentMapper<Debugger.Wrapable> mapper = ComponentMapper.getFor(Debugger.Wrapable.class);

    private boolean hasRenderer = false;
    private GameEntity gameEntity;

    @Override
    public void onComponentAdded(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    public boolean hasRenderer()
    {
        return hasRenderer;
    }

    @Override
    public void render(float deltatime, SpriteBatch batch, ShapeRenderer shapeRenderer)
    {
        hasRenderer = true;

        BitmapFont font = new BitmapFont();
        batch.begin();
        font.draw(batch, debugText, gameEntity.transform.px, gameEntity.transform.py);
        batch.end();
    }

    public static class Wrapable extends ComponentWrapable<Debugger>
    {

    }

    @Override
    protected ComponentWrapable<Debugger> getWrappable()
    {
        return new Wrapable();
    }

    public String debugText = "Debug me!";
}
