package donut.core.api.system.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.EntitySystem;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

public class PhysicSystem extends EntitySystem
{
    private Set<ContactListener> contactListeners = new HashSet<>();

    final World world;

    public PhysicSystem()
    {
        this.world = new World(new Vector2(0, 0), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.beginContact(contact);
                }
            }

            @Override
            public void endContact(Contact contact) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.endContact(contact);
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.preSolve(contact, oldManifold);
                }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.postSolve(contact, impulse);
                }
            }
        });
    }

    public World getWorld()
    {
        return world;
    }

    @Override
    public void onUpdate(EntitiesContainer entitiesContainer, float deltaTime) {
        world.step(deltaTime, 3, 3);
    }

    public PhysicBody getPhysicBody(BodyDef def)
    {
        return new PhysicBody(world, def);
    }

    public final ContactListener[] getContactListeners()
    {
        contactListeners.remove(null);
        return contactListeners.toArray(new ContactListener[0]);
    }

    public void addContactListener(ContactListener lisener)
    {
        contactListeners.add(lisener);
    }

    public void removeContactLisener(ContactListener lisener)
    {
        contactListeners.remove(lisener);
    }
}
