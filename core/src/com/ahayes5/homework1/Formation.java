package com.ahayes5.homework1;

import com.badlogic.gdx.utils.Array;
import java.util.concurrent.ThreadLocalRandom;

public class Formation
{
	private Array<Float> xPositions;
	private int formationType;//1 is waterfall from left, 2 is waterfall from right, 3 is arrow head
	// 5 is vertical line TODO
	//4 is horizontal line
	private int planeNum;
	public Formation(int p)
	{
		planeNum=p;
		formationType = ThreadLocalRandom.current().nextInt(1,4+1);
	}
	public Formation(int p, int t)
	{
		planeNum=p;
		formationType = t;
	}
	private void generateFormation()
	{
		for(int i=0;i<planeNum;i++)
		{
			switch (formationType)
			{
				case 1:
				case 4:
					xPositions.add(i*100f);
					break;
				case 2:
					xPositions.add(Homework.camera.viewportWidth - (i*100));
					break;
				case 3:
					float center = Homework.camera.viewportWidth/2;
					if(i==0)
						xPositions.add(center);
					else
					{
						xPositions.add(center+100*i);
						xPositions.add(center-100*i);
					}
					break;
			}
		}
	}
	public float nextPos() //returns -1 if none
	{
		return xPositions.removeIndex(0);
	}
	public boolean hasNext()
	{
		return !xPositions.isEmpty();
	}


}
