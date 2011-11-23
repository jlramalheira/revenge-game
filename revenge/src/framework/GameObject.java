package framework;

import javax.microedition.lcdui.Graphics;

public abstract class GameObject {

    public static final int DIRECTION_UP = 90;
    public static final int DIRECTION_DOWN = 270;
    public static final int DIRECTION_LEFT = 180;
    public static final int DIRECTION_RIGHT = 0;
    private double x;
    private double y;
    private double lastX;
    private double lastY;
    private double speedX;
    private double speedY;
    private double direction;
    private double speed;
    private int height;
    private int width;
    private boolean active;
    private boolean visible;

    public abstract void update();

    public abstract void paint(Graphics g);

    /////////////////////////////////////////////////////////////
    // RECURSOS PARA MOVIMENTO
    public void moveToLastPosition() {
        setX(getLastX());
        setY(getLastY());
    }

    public void moveBySpeedXY() {
        // guarda posicao atual (anterior)
        setLastX(getX());
        setLastY(getY());

        // move para nova posicao
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
    }
    
    public void moveBySpeedAndDirection() {
        setSpeedX(speed * Util.cos(direction));
        setSpeedY(speed * Util.sin(direction));
        moveBySpeedXY();
    }

    public void checkScreenBounds() {
        // encontrou o canto da tela em X
        if (getX() < 0) {
            setX(0);
        } else if ((getX() + getWidth()) > Screen.getWidth()) {
            setX(Screen.getWidth() - getWidth());
        }

        // encontrou o canto da tela em Y
        if (getY() < 0) {
            setY(0);
        } else if ((getY() + getHeight()) > Screen.getHeight()) {
            setY(Screen.getHeight() - getHeight());
        }
    }

//    public boolean collidesWith(GameObject obj) {
//        return (x < obj.x + obj.width
//                && obj.x < x + width
//                && y < obj.y + obj.height
//                && obj.y < y + height);
//    }
    
    public boolean outOfScreen() {
        return ((getX() < 0) || ((getX() + getWidth()) > Screen.getWidth()) || 
                (getY() < 0) || ((getY() + getHeight()) > Screen.getHeight()));
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        // limita a direcao
        if (direction >= 360) {
            direction -= 360;
        } else
        if (direction < 0) {
            direction += 360;
        }
        this.direction = direction;
    }

    public double getLastX() {
        return lastX;
    }

    public void setLastX(double lastX) {
        this.lastX = lastX;
    }

    public double getLastY() {
        return lastY;
    }

    public void setLastY(double lastY) {
        this.lastY = lastY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
