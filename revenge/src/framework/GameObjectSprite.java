package framework;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

public abstract class GameObjectSprite extends GameObject {

    public static final int TRANS_NONE = Sprite.TRANS_NONE;
    public static final int TRANS_ROT90 = Sprite.TRANS_ROT90;
    public static final int TRANS_ROT180 = Sprite.TRANS_ROT180;
    public static final int TRANS_ROT270 = Sprite.TRANS_ROT270;
    public static final int TRANS_MIRROR = Sprite.TRANS_MIRROR;
    public static final int TRANS_MIRROR_ROT180 = Sprite.TRANS_MIRROR_ROT180;
    public static final int TRANS_MIRROR_ROT270 = Sprite.TRANS_MIRROR_ROT270;
    public static final int TRANS_MIRROR_ROT90 = Sprite.TRANS_MIRROR_ROT90;
    private Sprite sprite;
    private int animationDelay;
    private int animationCount;
    private int[] animationCurrent;
    private Rectangle collisionRect = new Rectangle(0,0,0,0);
    private String name;

    public abstract void update();

    public abstract void paint(Graphics g);

    public abstract void collided(GameObjectSprite collidedObject);

    public GameObjectSprite() {
        this(Image.createImage(1, 1));
    }

    public GameObjectSprite(Image image, int frameWidth, int frameHeight) {
        sprite = new Sprite(image, frameWidth, frameHeight);
        setSprite(sprite);
        name = getClass().getName();
        // retira o nome do pacote, caso haja
        if (name.lastIndexOf('.') > 0) {
            name = name.substring(name.lastIndexOf('.') + 1);
        }
        setActive(true);
        setVisible(true);
    }

    public GameObjectSprite(Image image) {
        this(image, image.getWidth(), image.getHeight());
    }

    /**
     * Obtém o nome do objeto. Por padrão, o nome retornando é o nome da classe.
     * Caso o nome pode ser alterado via setNome().
     * @return Nome (da classe) do objeto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do objeto. Por padrão, o nome retornando é o nome da classe.
     * @param name Novo nome para o objeto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /////////////////////////////////////////////////////////////
    // ANIMACAO E DESENHO
    public void setTransformation(int transform) {
        sprite.setTransform(transform);
        sprite.setPosition((int)getX(), (int)getY());        
    }

    public void setAnimation(int[] animation) {
        // soh troca animacao se for diferente da atual
        if (animation != animationCurrent) {
            sprite.setFrameSequence(animation);
            animationCurrent = animation;
        }
    }

    public void setAnimationDelay(int delayPerFrame) {
        animationDelay = delayPerFrame;
    }

    public int getAnimationDelay() {
        return animationDelay;
    }
    
    public boolean animationEnded() {
        return (sprite.getFrame() == (sprite.getFrameSequenceLength()*animationDelay - 1));
    }
    
    public void setCurrentFrame(int frameIndex) {
        sprite.setFrame(frameIndex);
    }
    
    public int getCurrentFrame() {
        return sprite.getFrame();
    }
    
    public int[] getAnimation() {
        return animationCurrent;
    }
    
    public void animate() {
        if (animationCount++ > animationDelay) {
            sprite.nextFrame();
            animationCount = 0;
        }
    }

    public void drawSprite(Graphics g) {
        sprite.paint(g);
        if (GameEngine.mustShowInfo()) {
            drawInfo(g);
        }
    }

    public void drawSprite(Graphics g, double x, double y) {
        sprite.setPosition((int)x, (int)y);
        drawSprite(g);
        sprite.setPosition((int)getX(), (int)getY());
    }

    public void drawInfo(Graphics g) {
        g.setColor(0x0000FF); // collision box
        g.drawRect((int)getX(), (int)getY(), 2, 2);
        g.setColor(0x000000); // reference point
        g.drawRect(
                (int)getX() + (int)collisionRect.x,
                (int)getY() + (int)collisionRect.y,
                (int)collisionRect.width,
                (int)collisionRect.height);
    }

    public void setVisible(boolean visible) {
        sprite.setVisible(visible);
    }

    public boolean isVisible() {
        return sprite.isVisible();
    }

     /////////////////////////////////////////////////////////////
    // COLISAO

    public void defineCollisionRectangle(int offsetX, int offsetY, int width, int height) {
        // soh faz sentido para colisao simples (nao pixel-a-pixel)
        sprite.defineCollisionRectangle(offsetX, offsetY, width, height);
        collisionRect.x = offsetX;
        collisionRect.y = offsetY;
        collisionRect.width = width;
        collisionRect.height = height;
    }
    
    public Rectangle getBoundingRectangle() {
        double x = getX() + collisionRect.x;
        double y = getY() + collisionRect.y;
        return new Rectangle(x, y, collisionRect.width, collisionRect.height);
    }

    public void defineReferencePixel(int x, int y) {
        sprite.defineReferencePixel(x, y);
    }

    public void setReferencePixelPosition(int x, int y) {
        sprite.setRefPixelPosition(x, y);
    }
    
    public boolean collidesWith(GameObjectSprite objSprite) {
        if (sprite.collidesWith(objSprite.getSprite(), false)) {
            this.collided(objSprite);
            objSprite.collided(this);
            return true;
        }
        return false;
    }

    public boolean collidesWith(GameObjectSprite objSprite, boolean precise) {
        // por otimizacao, pergunta primeiro se colide com caixa de colisao
        if (sprite.collidesWith(objSprite.getSprite(), false)) {
            if (sprite.collidesWith(objSprite.getSprite(), precise)) {
                this.collided(objSprite);
                objSprite.collided(this);
                return true;
            }
        }
        return false;
    }

    public boolean collidesWith(TiledLayer layer, boolean precise) {
        if (sprite.collidesWith(layer, precise)) {
            this.collided(null);
            return true;
        }
        return false;
    }

    public boolean collidesWith(BackgroundTiled backgroundTiled, boolean precise) {
        if (sprite.collidesWith(backgroundTiled.getTiledLayer(), precise)) {
            this.collided(null);
            return true;
        }
        return false;
    }

    /////////////////////////////////////////////////////////////
    // SPRITE E MOVIMENTO
    
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        animationCount = 0;
        animationDelay = 0;
        collisionRect.x = 0;// offsetX
        collisionRect.y = 0;// offsetY
        collisionRect.width = sprite.getWidth();// width
        collisionRect.height = sprite.getHeight();// height
        setX(getX()); // forca atualizacao da posicao do novo sprite
        setY(getY());
    }
    
    public void defineSpriteByDirection() {
        // com o numero de frames do sprite, descubro 
        // a quantidade de o angulo de cada frame
        int frames = getSprite().getRawFrameCount();
        double angleStep = 45.0/(frames-1);
        
        // agora descubro qual o frame correto para o angulo
        // de direcao atual do objeto
        int frame = (int)(getDirection() / angleStep);
        frame = frame % frames;
        setCurrentFrame(frame); 
        
        // descobre em qual quadrante 45o esta o angulo
        int quadrante = (int)(getDirection() / 45);
        
        // ha frames de 0 ate 45 graus, o resto eh transformado
        // as rotacoes sao no sentido horario (clockwised)
        switch(quadrante) {
            case 0: setTransformation(TRANS_NONE); break;
            case 1: setTransformation(TRANS_NONE); break;
            case 2: setTransformation(TRANS_ROT270); break;
            case 3: setTransformation(TRANS_ROT270); break;
            case 4: setTransformation(TRANS_ROT180); break;
            case 5: setTransformation(TRANS_ROT180); break;
            case 6: setTransformation(TRANS_ROT90); break;
            case 7: setTransformation(TRANS_ROT90); break;
        }
    }

    public void followObjectX(GameObjectSprite target, double speed) {
        if (Math.abs(getX() - target.getX()) > speed) {
            if (getX() < target.getX()) {
                setSpeedX(speed);
//                setX(getX() + getSpeedX());
            } else {
                setSpeedX(-speed);
//                setX(getX() + getSpeedX());
            }
        } else {
            setX(target.getX());
            setSpeedX(0);
        }
    }

    public void followObjectY(GameObjectSprite target, double speed) {
        if (Math.abs(getY() - target.getY()) > speed) {
            if (getY() < target.getY()) {
                setSpeedY(speed);
//                setY(getY() + getSpeedY());
            } else {
                setSpeedY(-speed);
//                setY(getY() + getSpeedY());
            }
        } else {
            setY(target.getY());
            setSpeedY(0);
        }
    }

    public double getXOnScreen() {
        return getX() - Map.getViewX();
    }
    
    public double getYOnScreen() {
        return getY() - Map.getViewY();
    }
    
    public double getCenterX() {
        return getX() + getWidth()/2;
    }
    
    public double getCenterY() {
        return getY() + getHeight()/2;
    }
    
    public void setCenterX(double x) {
        setX(x-getWidth()/2);
    }
    
    public void setCenterY(double y) {
        setY(y-getHeight()/2);
    }
    
    public void setX(double x) {
        super.setX(x);
        sprite.setPosition((int)x, (int)getY());
    }

    public void setY(double y) {
        super.setY(y);
        sprite.setPosition((int)getX(), (int)y);
    }

    // eh preciso deixar estes dois vazios
    // apenas para nao fazer o que a classe pai faz
    public void setWidth(int width) {
    }

    public void setHeight(int width) {
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }
}

/*
        TENTANDO ROTACIONAR UM SPRITE COM FRAMES ATE 45 GRAUS EM 360 USANDO TRANSFORMACOES
  
        double dir = getDirection();
        
        // com o numero de frames do sprite, descubro 
        // a quantidade de o angulo de cada frame
        int frames = getSprite().getRawFrameCount();
        double angleStep = 45.0/(frames-1);
        
        // agora descubro qual o frame correto para o angulo
        // de direcao atual do objeto
        int frame = (int)(dir / angleStep);
        frame = frame % frames;
        
        setCurrentFrame(0); 
        
        System.out.println("FRAMES="+frames+" STEP="+angleStep+" FRAME: "+frame);
        
        // descobre em qual quadrante 45o esta o angulo
        int quadrante = (int)(dir / 45);
        
        // ha frames de 0 ate 45 graus, o resto eh transformado
        // as rotacoes sao no sentido horario (clockwised)
        switch(quadrante) {
            case 0: setTransformation(TRANS_NONE); break;
            case 1: setTransformation(TRANS_NONE); break;
            case 2: setTransformation(TRANS_ROT270); break;
            case 3: setTransformation(TRANS_ROT270); break;
            case 4: setTransformation(TRANS_ROT180); break;
            case 5: setTransformation(TRANS_ROT180); break;
            case 6: setTransformation(TRANS_ROT90); break;
            case 7: setTransformation(TRANS_ROT90); break;
        }
  
 */
