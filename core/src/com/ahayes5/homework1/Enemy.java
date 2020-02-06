package com.ahayes5.homework1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Plane
{
	private int hp;
	private Vector2 position,dimensions,bounds;
	private int speed,pointVal;
	private Sprite sprite;
	private float rotation;
	public Enemy(Texture txt, Polygon p,int h,int s,Vector2 b,int pV,Vector2 size) //Random spawn
	{
		super(txt,p);
		hp=h;
		speed=s;
		bounds =b;
		pointVal=pV;
		position = new Vector2(size.x+ (float)Math.random() * bounds.x+size.x,bounds.y+size.y);
		dimensions=size;
		rotation = 180;
		p.rotate(rotation);
		collider.setPosition(position.x,position.y);
		sprite = new Sprite(texture);
		sprite.setSize(size.x,size.y);
	}
	public Enemy(Texture txt, Polygon p,int h, int s, Vector2 b,int pV,Vector2 size,Vector2 pos)
	{
		super(txt,p);
		hp=h;
		speed=s;
		position=pos;
		pointVal=pV;
		bounds=b;
		dimensions=size;
		rotation=180;
		p.rotate(rotation);
		collider.setPosition(position.x,position.y);
		sprite=new Sprite(texture);
		sprite.setSize(size.x,size.y);
	}
	public void move()
	{
		collider.setPosition(collider.getX(),collider.getY() - (speed*Gdx.graphics.getDeltaTime()));
		//collider.translate(0,-speed* Gdx.graphics.getDeltaTime());
	}
	@Override
	public void draw(Batch batch)
	{
		sprite.setOrigin(collider.getOriginX(),collider.getOriginY());
		sprite.setRotation(rotation);
		sprite.setPosition(collider.getX(),collider.getY());
		sprite.draw(batch);
	}

	@Override
	public void explode()
	{

	}
}
