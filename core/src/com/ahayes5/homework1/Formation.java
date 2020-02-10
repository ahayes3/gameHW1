package com.ahayes5.homework1;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.concurrent.ThreadLocalRandom;

public class Formation
{
	//private Array<Enemy> enemies;
	private int formationType;//1 is waterfall from left, 2 is waterfall from right, 3 is arrow head
	// 5 is vertical line
	//4 is horizontal line
	private float top;
	private EnemyTemplate enemy;
	private int planeNum;

	public Formation(int p, EnemyTemplate e)
	{
		planeNum = p;
		enemy = e;
		top = Homework.camera.viewportHeight + enemy.collider.getBoundingRectangle().height;
		formationType = -1;
	}

	public Formation(int p, int t, EnemyTemplate e)
	{
		planeNum = p;
		formationType = t;
		enemy = e;
		top = Homework.camera.viewportHeight + enemy.collider.getBoundingRectangle().height;
	}

	public Array<Enemy> generateFormation(int type)
	{
		Array<Enemy> enemies=new Array<>();
		int row;
		int column;
		float min = enemy.collider.getBoundingRectangle().width;
		float max = Homework.camera.viewportWidth;
		Vector2 position =new Vector2();
		switch (type)
		{
			case 1:
				row=1;
				column = 1;
				for(int i=0;i<planeNum;i++)
				{
					boolean placed=false;
					while(!placed)
					{
						position.x = (column*enemy.dimensions.x) +5;
						position.y = top + row*enemy.dimensions.y +5;
						if(position.x>max)
						{
							column = 1;
						}
						else
						{
							enemies.add(enemy.createEnemy(new Vector2(position)));
							placed=true;
							row++;
							column++;
						}
					}
				}
				break;
			case 2:
				row=1;
				column = 0;
				for(int i=0;i<planeNum;i++)
				{
					boolean placed=false;
					while(!placed)
					{
						position.x = max - (column * enemy.dimensions.x) - 5;
						position.y = top + row * enemy.dimensions.y + 5;

						if (position.x < min)
						{
							row++;
							column = 0;
						}
						else
						{
							enemies.add(enemy.createEnemy(new Vector2(position)));
							placed = true;
							row++;
							column++;
						}
					}
				}

				break;
			case 3:
				float xPos= ThreadLocalRandom.current().nextInt(Math.round(min), Math.round(Homework.camera.viewportWidth));
				row=0;
				for(int i=0;i<planeNum;i++)
				{
					position.x = xPos;
					position.y = top+ row*enemy.dimensions.y +5;
					enemies.add(enemy.createEnemy(new Vector2(position)));
					row++;
				}
				break;
			case 4:
				row = 1;
				column = 1;


				for (int i = 0; i < planeNum; i++)
				{
					boolean placed = false;
					while (!placed)
					{
						position.x = column * (enemy.dimensions.x)+5;
						position.y =top + (row * enemy.dimensions.y)+10;
						if (position.x < max)
						{
							enemies.add((enemy.createEnemy(new Vector2(position))));
							column++;
							placed=true;
						}
						else
						{
							column=1;
							row++;
						}
					}
				}
				break;
		}
		return enemies;
	}
	public Array<Enemy> generateFormation()
	{
		if(formationType!=-1)
			return generateFormation(formationType);
		return generateFormation(ThreadLocalRandom.current().nextInt(1, 4 + 1));
	}

}
