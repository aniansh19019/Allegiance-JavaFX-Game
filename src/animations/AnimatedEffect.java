package animations;

import javafx.scene.image.Image;
import util.Sprite;

import java.io.IOException;
import java.util.ArrayList;

//in progress
public abstract class AnimatedEffect extends Sprite
{
    private double animationSpeed;
    private transient ArrayList<Image> frames;
    private long counter;
    private int frameNumber;
    private boolean isFinished;
    private boolean loopFrames;

    public double getAnimationSpeed()
    {
        return animationSpeed;
    }

    public void setAnimationSpeed(double animationSpeed)
    {
        this.animationSpeed = animationSpeed;
    }

    public boolean isFinished()
    {
        return isFinished;
    }

    public void setFinished(boolean finished)
    {
        isFinished = finished;
    }

    public boolean isLoopFrames()
    {
        return loopFrames;
    }

    public void setLoopFrames(boolean loopFrames)
    {
        this.loopFrames = loopFrames;
    }

    public ArrayList<Image> getFrames()
    {
        return frames;
    }

    public void setFrames(ArrayList<Image> frames)
    {
        this.frames = frames;
    }

    public abstract void loadFrames();

    public AnimatedEffect()
    {
        animationSpeed=1;
        counter=0;
        frameNumber=0;
        isFinished=false;
        loopFrames=false;
        frames = new ArrayList<Image>();
    }

    private void updateCounter()
    {
        counter= (counter+1)%(int)(1/animationSpeed);
    }

    @Override
    public void update()
    {
        super.update();
        updateCounter();
        if(counter==0) // counter reset
        {
            if(frameNumber==frames.size())
            {
                if(isLoopFrames())
                {
                    frameNumber = 0;
                }
                else
                {
                    isFinished = true;
                }
            }

            if(!isFinished)
            {
                switchImage(frames.get(frameNumber++)); // set next frame
            }

        }
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();//read object
        //check frame number
        frames = new ArrayList<Image>();
        loadFrames();
        switchImage(frames.get(frameNumber%frames.size()));
//        setPosition(getPosition());
    }
}
