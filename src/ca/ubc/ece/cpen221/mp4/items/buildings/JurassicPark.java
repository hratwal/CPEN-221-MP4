package ca.ubc.ece.cpen221.mp4.items.buildings;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.BuildingAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;

/**
 *  will be built by the {@link Human} whenever the Human has enough
 * energy
 */
public class JurassicPark implements Item, Actor {
    private final static ImageIcon jurassicImage = Util.loadImage("jurassic.gif");
    private static final int INITIAL_ENERGY = 500;
    private static final int STRENGTH = 1000;
    private int energy = INITIAL_ENERGY;
    private Location location;
    private boolean isDead;
    private BuildingAI ai = new BuildingAI();

    /**
     * Build Jurassic Park at <code> location </code>. The location must be valid and
     * empty
     *
     * @param location
     *            : the location where this grass will be created
     */
    public JurassicPark(Location location) {
        this.location = location;
        this.isDead = false;
    }

    @Override
    public ImageIcon getImage() {
        return jurassicImage;
    }

    @Override
    public String getName() {
        return "Jurassic Park";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getPlantCalories() {
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return 0;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    public void moveTo(Location targetLocation) {
        location = targetLocation;

    }
    
    @Override
    public int getCoolDownPeriod() {
        return 1;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
    }

}