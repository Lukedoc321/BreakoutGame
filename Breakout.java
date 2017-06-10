/*
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int DELAY = 10;
	
	
	
	

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setup();
		play();
	}
	
	
	
	
/* Set up the bricks, paddle, and ball */
	
	private void setup() {
		makeBricks();
		makePaddle();
		makeBall();
	}
	
	
	
	
/* Make the bricks */

	private void makeBricks() {
		for (int row = 0; row < 10; row++) {
			for (int brickNum = 0; brickNum < 10; brickNum++) {
				double x = ((getWidth() - ((BRICK_WIDTH + BRICK_SEP) * 10)) / 2) + (brickNum * (BRICK_WIDTH + BRICK_SEP));
				double y = ((row * (BRICK_HEIGHT + BRICK_SEP)) + BRICK_Y_OFFSET);
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if (row < 2) {
					brick.setColor(Color.RED);
				} else if (row >= 2 && row < 4) {
					brick.setColor(Color.ORANGE);
				} else if (row >= 4 && row < 6) {
					brick.setColor(Color.YELLOW);
				} else if (row >= 6 && row < 8) {
					brick.setColor(Color.GREEN);
				} else {
					brick.setColor(Color.CYAN);
				}
				add(brick);
			}
		}
	}
	
	
	
/* Make the paddle */
	
	private void makePaddle() {
		double x = getWidth() / 2 - PADDLE_WIDTH / 2;
		double y = getHeight() - (PADDLE_Y_OFFSET + PADDLE_HEIGHT);
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}
	
	
	
/* Make paddle move with mouse */
	
	public void mouseMoved(MouseEvent e) {
		if ((e.getX() < (getWidth() - PADDLE_WIDTH / 2)) && (e.getX() > PADDLE_WIDTH / 2)) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - (PADDLE_Y_OFFSET + PADDLE_HEIGHT));
		}
	}
	
	
	
/* Make the ball */

	private void makeBall() {
		double x = getWidth() / 2 - BALL_RADIUS;
		double y = getHeight() / 2 - BALL_RADIUS;
		GOval ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball);
	}
	
	
	
	

/* Start the game/ball movement */
	
	private void play() {
		getBallVelocity();
		while (true) {
			moveBall();
		}
	}
	
/* Calculate the ball's velocity */
	
	private void getBallVelocity() {
		vx = rgen.nextDouble(1.0,3.0);
		vy = 3.0;
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
	}
	
/* Move the ball */
	
	private void moveBall() {
		ball.move(vx, vy);
		pause (DELAY);
	}
	

	//instance variables for velocity of ball
	private double vx, vy;
	
	//random generator object
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	//paddle object
	private GRect paddle;
	
	//ball object
	private GOval ball;
	

}
