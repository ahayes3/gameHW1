package com.ahayes5.homework1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class EnemyTemplate
{
	public Texture texture;
	public Polygon collider;
	public int hp;
	public Vector2 position,dimensions,bounds;
	public int speed,pointVal;
	public Sprite sprite;
	public float rotation;
	public EnemyTemplate(Texture txt, Polygon p,int h,int s,Vector2 b,int pV,Vector2 size) //Random spawn
	{
		texture=txt;
		collider=p;
		hp=h;
		speed=s;
		bounds =b;
		pointVal=pV;
		position = new Vector2(size.x+ (float)Math.random() * bounds.x+size.x,bounds.y+size.y);
		dimensions=size;
		rotation = 180;
		p.rotate(rotation);
		collider.setPosition(position.x,position.y);
	}
	public EnemyTemplate(Texture txt, Polygon p,int h, int s, Vector2 b,int pV,Vector2 size,Vector2 pos)
	{
		texture=txt;
		collider=p;
		hp=h;
		speed=s;
		position=pos;
		pointVal=pV;
		bounds=b;
		dimensions=size;
		rotation=180;
		p.rotate(rotation);
		collider.setPosition(position.x,position.y);
	}
	public Enemy createEnemy()
	{
		return new Enemy(texture,collider,hp,speed,bounds,pointVal,dimensions,position);
	}
	public Enemy createEnemy(Vector2 pos)
	{
		return new Enemy(texture,new Polygon(collider.getVertices()),hp,speed,bounds,pointVal,dimensions,pos);
	}
}
