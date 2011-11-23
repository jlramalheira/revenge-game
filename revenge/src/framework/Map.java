/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.TiledLayer;

/**
 * @author marcos
 */
public class Map {

    private TiledLayer tiledLayer;
    private LayerManager layerManager;
    private static double viewX;
    private static double viewY;
    private int viewWidth;
    private int viewHeight;
    private int tileWidth;
    private int tileHeight;
    private int mapRows;
    private int mapColumns;
    private boolean[] collidableTiles;
    private double viewSpeed;
    private static Map instance;
    private int numberOfTiles;
    private Vector mapsTextData;
    private String mapFileTmx;
    private Image tilesImageTmx;

    private Map() {
        layerManager = new LayerManager();

        // valores padrao
        viewWidth = Screen.getWidth();
        viewHeight = Screen.getHeight();
        viewSpeed = 10;
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public void reset() {
        // remove qualquer objeto anterior
        while (layerManager.getSize() > 0) {
            layerManager.remove(layerManager.getLayerAt(layerManager.getSize() - 1));
        }
        System.out.println("Map.reset: Remaining objets: " + layerManager.getSize());
    }

    private Vector split(String text, char separator) {
        Vector tokens = new Vector(100);
        String token = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == separator) {
                tokens.addElement(token);
                token = "";
            } else {
                token += text.charAt(i);
            }
        }
        // adicionando ultimo elemento
        tokens.addElement(token);
        return tokens;
    }
    
    public void setTiledLayerTmx() {
        // mapa 0 eh o mapa do jogo
        setTiledLayer(extractTiledLayer(0), tilesImageTmx);
    }
    
    public void extractGameObjectsTmx(MapObjectsInitializer objInitializer) {
        // mapa 1 eh o mapa de objetos do jogo
        extractGameObjects(objInitializer, extractTiledLayer(1), tilesImageTmx);
    }

    private TiledLayer extractTiledLayer(int mapIndex) {
        
        if (mapsTextData == null) {
            mapsTextData = readMapFile(mapFileTmx);
        }
        // sao dois mapas
        Vector mapText = (Vector) mapsTextData.elementAt(mapIndex);
        if (mapText == null) {
            System.out.println("Map.setTiledLayerTmx: arquivo TMX nao possui mapa.");
        }
        
        // cria o tiled layer e o preenche
        TiledLayer tiledLayer = new TiledLayer(mapColumns, mapRows, tilesImageTmx, tileWidth, tileHeight);
        for (int row = 0; row < mapRows; row++) {
            Vector line = split(mapText.elementAt(row).toString(), ',');

            for (int col = 0; col < mapColumns; col++) {
                int tile = Integer.parseInt(line.elementAt(col).toString());
                tiledLayer.setCell(col, row, tile);
            }
        }
        
        return tiledLayer;
    }
    
    private Vector readMapFile(String fileName) {
        try {
            FileReader.open(fileName);
            Vector mapLayers = new Vector(2);

            // obtendo informacoes do mapa
            String line = FileReader.readLine();

            System.out.println("Map.ReadMapFile:");
            
            // 2a linha contem tamanho do tile
            line = FileReader.readLine();
            int start = line.indexOf("width=");
            mapColumns = Integer.parseInt(line.substring(start + 7, line.indexOf("\"", start + 8)));
            start = line.indexOf("height=");
            mapRows = Integer.parseInt(line.substring(start + 8, line.indexOf("\"", start + 9)));
            System.out.println("map columns="+mapColumns);
            System.out.println("map rows="+mapRows);
            
            start = line.indexOf("tilewidth=");
            tileWidth = Integer.parseInt(line.substring(start + 11, line.indexOf("\"", start + 12)));
            start = line.indexOf("tileheight=");
            tileHeight = Integer.parseInt(line.substring(start + 12, line.indexOf("\"", start + 13)));
            System.out.println("map tilewidth="+tileWidth);
            System.out.println("map tileheight="+tileHeight);

            while (true) {
                // pulando tags XML
                line = FileReader.readLine();
                while (line.indexOf("<") != -1) {
                    // acabou o arquivo de mapa
                    if (line.indexOf("</map>") != -1) {
                        return mapLayers;
                    }
                    line = FileReader.readLine();
                }

                // chegou nas linhas de tiles do mapa
                System.out.println("\nMAPA ENCONTRADO:");
                Vector layer = new Vector(mapRows);
                while (line.indexOf("<") == -1) {
                    layer.addElement(line);
                    System.out.println(line);
                    line = FileReader.readLine();
                }
                // adicionar a camada na lista
                mapLayers.addElement(layer);
            }
            
        } catch (Exception ex) {
            System.out.println("Map.readMapFile: ERRO nao foi possivel ler arquivo " + fileName);
            return null;
        }
    }

    public void setTiledLayer(TiledLayer mapLayer, Image tilesImage) {
        this.tiledLayer = mapLayer;
        tileWidth = tiledLayer.getCellWidth();
        tileHeight = tiledLayer.getCellHeight();
        layerManager.append(mapLayer);

        // eh preciso contar o tile zero, que nao esta na imagem
        numberOfTiles = (tilesImage.getWidth() / tileWidth)
                * (tilesImage.getHeight() / tileHeight) + 1;

        collidableTiles = new boolean[numberOfTiles];
        // tile 0 eh sempre passavel
        for (int i = 1; i < collidableTiles.length; i++) {
            collidableTiles[i] = true;
        }
    }

    public void extractGameObjects(MapObjectsInitializer objInitializer, TiledLayer objectsLayer,
            Image tilesImage) {
        // percorre a camada dos objetos: quando encontra objeto, devolve 
        // eh preciso contar o tile zero, que nao esta na imagem
        numberOfTiles = (tilesImage.getWidth() / objectsLayer.getCellWidth())
                * (tilesImage.getHeight() / objectsLayer.getCellHeight()) + 1;

        System.out.println("tiles=" + numberOfTiles);

        // percore as linhas
        for (int row = 0; row < objectsLayer.getRows(); row++) {
            // percorre as colunas
            for (int col = 0; col < objectsLayer.getColumns(); col++) {
                int tile = objectsLayer.getCell(col, row);
                // encontrou um tile nao vazio, eh objeto
                if ((tile != 0)) {
                    System.out.println("ACHOU OBJ=" + tile + " x=" + col * objectsLayer.getCellWidth() + " y=" + row * objectsLayer.getCellHeight());
                    GameObjectSprite obj = objInitializer.mapObjectFound(tile);
                    if (obj == null) {
                        System.out.println("Map.extractGameObjects: ERRO, objeto NULL para o tile "+tile);
                    } else {
                        obj.setX(col * objectsLayer.getCellWidth());
                        obj.setY(row * objectsLayer.getCellHeight());
                        addGameObject(obj);
                    }
                }
            }
        }
    }

    public void addGameObject(GameObjectSprite gameObject) {
        // coloco eles em uma lista?
        layerManager.append(gameObject.getSprite());
    }

    public void removeGameObject(GameObjectSprite gameObject) {
        layerManager.remove(gameObject.getSprite());
    }

    public void addObjectsCollection(ObjectsCollection objectsCollection) {
        Vector objects = objectsCollection.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            layerManager.append(((GameObjectSprite) objects.elementAt(i)).getSprite());
        }
    }

    public void setPassableTiles(int[] passables) {
        for (int i = 0; i < passables.length; i++) {
            collidableTiles[passables[i]] = false;
        }
    }

    public boolean isTilePassable(int tile) {
        return !collidableTiles[tile];
    }

    public int getTile(double x, double y) {
        return tiledLayer.getCell(
                (int) (x / tiledLayer.getCellWidth()),
                (int) (y / tiledLayer.getCellHeight()));
    }

    public void setTile(double x, double y, int tileIndex) {
        tiledLayer.setCell(
                (int) (x / tiledLayer.getCellWidth()),
                (int) (y / tiledLayer.getCellHeight()), tileIndex);
    }

    public void checkMapBoundsForObject(GameObjectSprite gameObject) {
        if (gameObject.getX() < 0) {
            gameObject.setX(0);
        }
        if (gameObject.getY() < 0) {
            gameObject.setY(0);
        }
        if ((gameObject.getX() + gameObject.getWidth()) > tiledLayer.getWidth()) {
            gameObject.setX(tiledLayer.getWidth() - gameObject.getWidth());
        }
        if ((gameObject.getY() + gameObject.getHeight()) > tiledLayer.getHeight()) {
            gameObject.setY(tiledLayer.getHeight() - gameObject.getHeight());
        }
    }

    public void checkMapBoundsForView() {
        // impedindo que a view saia do mapa
        if (viewX < 0) {
            viewX = 0;
        }
        if (viewY < 0) {
            viewY = 0;
        }
        if ((viewX + viewWidth) > tiledLayer.getWidth()) {
            viewX = tiledLayer.getWidth() - viewWidth;
        }
        if ((viewY + viewHeight) > tiledLayer.getHeight()) {
            viewY = tiledLayer.getHeight() - viewHeight;
        }
    }

    public int checkForTileUnder(GameObjectSprite obj) {
        // verificando se ha tile abaixo

        // ponto esquerda inferior
        int tile = 0;
        Rectangle rect = obj.getBoundingRectangle();
        tile = getTile(rect.x, rect.y + rect.height);
        // se ha tile e ele colide
        if (collidableTiles[tile]) {
            return tile;
        }

        // ponto direita inferior
        tile = 0;
        rect = obj.getBoundingRectangle();
        tile = getTile(rect.x + rect.width - 1, rect.y + rect.height);
        // se ha tile e ele colide
        if (collidableTiles[tile]) {
            return tile;
        }
        return 0;
    }

    /**
     * @deprecated 
     */
    private int checkforTileUnder2(GameObjectSprite obj) {
        // verificando se mario esta no chao
        int tile = 0;
        tile = getTile(obj.getX() + 5,
                obj.getY() + obj.getHeight());
        // se ha tile e ele colide
        if (collidableTiles[tile]) {
            return tile;
        }

        // se ha tile e ele colide
        tile = getTile(obj.getX() + obj.getWidth() - 5,
                obj.getY() + obj.getHeight());
        if (collidableTiles[tile]) {
            return tile;
        }

        return 0;
    }

    public Rectangle getTileBounds(int col, int row) {
        return new Rectangle(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
    }

    private Bounds getTilesForObject(Rectangle objBounds) {
        int leftTile = (int) (objBounds.x / tileWidth);
        int rightTile = (int) ((objBounds.x + objBounds.width + 0.5) / tileWidth);
        int topTile = (int) (objBounds.y / tileHeight);
        int bottomTile = (int) ((objBounds.y + objBounds.height + 0.5) / tileHeight);

        leftTile = Math.max(leftTile, 0);
        rightTile = Math.min(rightTile, getMapTilesColumns() - 1);
        topTile = Math.max(topTile, 0);
        bottomTile = Math.min(bottomTile, getMapTilesRows() - 1);

        return new Bounds(leftTile, rightTile, topTile, bottomTile);
    }

    public boolean checkMapCollisions(GameObjectSprite obj) {
        // desfaz movimento da nave
        obj.setX(obj.getX() - obj.getSpeedX());
        obj.setY(obj.getY() - obj.getSpeedY());
        boolean collided = false;

        // move em X
        obj.setX(obj.getX() + obj.getSpeedX());
        Rectangle objBounds = obj.getBoundingRectangle();
        Bounds tile = getTilesForObject(objBounds);

        // For each potentially colliding tile,
        for (int row = (int) tile.top; row <= tile.bottom; ++row) {
            for (int col = (int) tile.left; col <= tile.right; ++col) {
                // If this tile is collidable,
                if (!isTilePassable(tiledLayer.getCell(col, row))) {
                    collided = true;
                    // Determine collision depth (with direction) and magnitude.
                    Rectangle tileBounds = getTileBounds(col, row);
                    Point depth = Util.getIntersectionDepth(objBounds, tileBounds);
                    obj.setX(obj.getX() + depth.x);
                    // update collision bounds for object
                    objBounds = obj.getBoundingRectangle();
                }
            }
        }

        // move em Y
        obj.setY(obj.getY() + obj.getSpeedY());
        objBounds = obj.getBoundingRectangle();
        tile = getTilesForObject(objBounds);

        // For each potentially colliding tile,
        for (int row = (int) tile.top; row <= tile.bottom; ++row) {
            for (int col = (int) tile.left; col <= tile.right; ++col) {
                // If this tile is collidable,
                if (!isTilePassable(tiledLayer.getCell(col, row))) {
                    collided = true;
                    // Determine collision depth (with direction) and magnitude.
                    Rectangle tileBounds = getTileBounds(col, row);
                    Point depth = Util.getIntersectionDepth(objBounds, tileBounds);
                    obj.setY(obj.getY() + depth.y);
                    // update collision bounds for object
                    objBounds = obj.getBoundingRectangle();
                }
            }
        }
        return collided;
    }

    /**
     * @deprecated 
     */
    private boolean checkMapCollisions_xna(GameObjectSprite obj) {
        // Get the player's bounding rectangle and find neighboring tiles.
        Rectangle objBounds = obj.getBoundingRectangle();
        int leftTile = (int) Math.floor(objBounds.x / tileWidth);
        int rightTile = (int) Math.floor(((objBounds.x + objBounds.width) / tileWidth));
        int topTile = (int) Math.floor(objBounds.y / tileHeight);
        int bottomTile = (int) Math.floor(((objBounds.y + objBounds.height) / tileHeight));

        leftTile = Math.max(leftTile, 0);
        rightTile = Math.min(rightTile, getMapTilesColumns() - 1);
        topTile = Math.max(topTile, 0);
        bottomTile = Math.min(bottomTile, getMapTilesRows() - 1);

//        System.out.println(leftTile+","+rightTile+","+topTile+","+bottomTile);

        boolean collided = false;

        // For each potentially colliding tile,
        for (int row = topTile; row <= bottomTile; ++row) {
            for (int col = leftTile; col <= rightTile; ++col) {
                // If this tile is collidable,
                if (!isTilePassable(tiledLayer.getCell(col, row))) {
                    collided = true;

                    // Determine collision depth (with direction) and magnitude.
                    Rectangle tileBounds = getTileBounds(col, row);
                    Point depth = Util.getIntersectionDepth(objBounds, tileBounds);

                    // move the object outside the tile collision bounds
                    if (!depth.isZero()) {
                        double absDepthX = Math.abs(depth.x);
                        double absDepthY = Math.abs(depth.y);

                        System.out.println("DepthX:" + absDepthX + " DepthY:" + absDepthY);

                        if (absDepthY < absDepthX) {
                            obj.setY((int) obj.getY() + (int) depth.y);
                        } else {
                            obj.setX((int) obj.getX() + (int) depth.x);
                        }
                        // update collision bounds for object
                        objBounds = obj.getBoundingRectangle();
                    }
                }
            }
        }
        return collided;
    }

    /**
     * @deprecated 
     */
    private boolean checkMapCollisions_move(GameObjectSprite obj) {
        boolean collided = false;
        // impede que o personagem passe pelos blocos
        // atualiza posicao do mario e verifica colisao com mapa
        // desfaz movimento da nave
        obj.setX(obj.getX() - obj.getSpeedX());
        obj.setY(obj.getY() - obj.getSpeedY());

        // move em X, se colidir, volta
        obj.setX(obj.getX() + obj.getSpeedX());
        if (obj.collidesWith(tiledLayer, false)) {
            System.out.println("AQUI");
            collided = true;
            // encosta no bloco colidido
            moveToContactX(obj);
        }
        // move em Y, se colidir, volta
        obj.setY(obj.getY() + obj.getSpeedY());
        if (obj.collidesWith(tiledLayer, false)) {
            collided = true;
            // encosta no bloco colidido
            moveToContactY(obj);
        }
        return collided;
    }

    /**
     * @deprecated 
     */
    private void moveToContactY(GameObjectSprite obj) {
        // encosta no bloco colidido:
        int stepBack;
        if (obj.getSpeedY() > 0) {
            stepBack = -1;
        } else {
            stepBack = 1;
        }

        // volta ate nao colidir mais
        obj.setY(obj.getY() + stepBack);
        while (obj.collidesWith(tiledLayer, false)) {
            obj.setY(obj.getY() + stepBack);
        }
    }

    /**
     * @deprecated 
     */
    private void moveToContactX(GameObjectSprite obj) {
        // encosta no bloco colidido:
        int stepBack;
        if (obj.getSpeedX() > 0) {
            stepBack = -1;
        } else {
            stepBack = 1;
        }

        // volta ate nao colidir mais
        obj.setX(obj.getX() + stepBack);
        while (obj.collidesWith(tiledLayer, false)) {
            obj.setX(obj.getX() + stepBack);
        }
    }

    public void viewFollow(GameObjectSprite gameObject) {
        // posicionando a camera de forma que o pĺayer sempre
        // fique no centro da tela
        double targetX = gameObject.getX() - Screen.getWidth() / 2 + gameObject.getWidth() / 2;
        double targetY = gameObject.getY() - Screen.getHeight() / 2 + gameObject.getHeight() / 2;

        // busca player em X
        if (Math.abs(viewX - targetX) > viewSpeed) {
            if (viewX < targetX) {
                viewX += viewSpeed;
            } else {
                viewX -= viewSpeed;
            }
        } else {
            viewX = targetX;
        }

        // busca player em Y
        if (Math.abs(viewY - targetY) > viewSpeed) {
            if (viewY < targetY) {
                viewY += viewSpeed;
            } else {
                viewY -= viewSpeed;
            }
        } else {
            viewY = targetY;
        }
    }

    public void moveViewByKeys() {
        if (Key.UP_PRESSED) {
            viewY -= viewSpeed;
        } else if (Key.DOWN_PRESSED) {
            viewY += viewSpeed;
        }
        if (Key.LEFT_PRESSED) {
            viewX -= viewSpeed;
        } else if (Key.RIGHT_PRESSED) {
            viewX += viewSpeed;
        }
    }

    public boolean followsAndCollides(GameObjectSprite object) {
        boolean collided = checkMapCollisions(object);
        viewFollow(object);
        checkMapBoundsForObject(object);
        checkMapBoundsForView();
        return collided;
    }

    public void update() {
    }

    public void paint(Graphics g) {
        layerManager.setViewWindow((int) viewX, (int) viewY, viewWidth, viewHeight);
        layerManager.paint(g, 0, 0);
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public double getViewSpeed() {
        return viewSpeed;
    }

    public void setViewSpeed(double viewSpeed) {
        this.viewSpeed = viewSpeed;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public int getMapWidth() {
        return tiledLayer.getWidth();
    }

    public int getMapHeight() {
        return tiledLayer.getHeight();
    }

    public int getMapTilesColumns() {
        return tiledLayer.getColumns();
    }

    public int getMapTilesRows() {
        return tiledLayer.getRows();
    }

    public void setView(double x, double y) {
        viewX = x;
        viewY = y;
    }

    public static double getViewX() {
        return viewX;
    }

    public void setViewX(double viewX) {
        this.viewX = viewX;
    }

    public static double getViewY() {
        return viewY;
    }

    public void setViewY(double viewY) {
        this.viewY = viewY;
    }

    public String getMapFileTmx() {
        return mapFileTmx;
    }

    public void setMapFileTmx(String mapFileTmx) {
        this.mapFileTmx = mapFileTmx;
    }

    public Image getMapTilesImage() {
        return tilesImageTmx;
    }

    public void setMapTilesImage(String mapTilesImage) {
        this.tilesImageTmx = Util.loadImage(mapTilesImage);
    }
    
}
/*
    public boolean checkMapCollisions(GameObjectSprite obj) {
        // impede que o personagem passe pelos blocos
        // atualiza posicao do mario e verifica colisao com mapa
        // desfaz movimento da nave
        obj.setX(obj.getX()-obj.getSpeedX());
        obj.setY(obj.getY()-obj.getSpeedY());

        double speedStepX = obj.getSpeedX();
        boolean checked = false;
        while (!checked) {
            // se o objeto atravessa um tile sem colidir, faz a checagemd e colisao em passos
            if ((Math.abs(speedStepX)-obj.getWidth()) > tiledLayer.getCellWidth()) {
                if (speedStepX > 0)
                    speedStepX -= tiledLayer.getCellWidth();
                else
                    speedStepX += tiledLayer.getCellWidth();
            } else {
                checked = true;
            }
            
            // move em X, se colidir, volta
            obj.setX(obj.getX()+speedStepX);
            if (obj.collidesWith(tiledLayer, false)) {
                // encosta no bloco colidido:
                int stepBack;
                if (speedStepX > 0) 
                    stepBack = -1;
                else
                    stepBack = 1;
                // volta ate nao colidir mais
                obj.setX(obj.getX()+stepBack);
                while (obj.collidesWith(tiledLayer, false)) {
                    obj.setX(obj.getX()+stepBack);
                }
                checked = true;
            }
        }

        double speedStepY = obj.getSpeedY();
        checked = false;
        while (!checked) {
            // se o objeto atravessa um tile sem colidir, faz a checagemd e colisao em passos
            if ((Math.abs(speedStepY) - obj.getHeight()) > tiledLayer.getCellHeight()) {
                if (speedStepY > 0) {
                    speedStepY -= tiledLayer.getCellHeight();
                } else {
                    speedStepY += tiledLayer.getCellHeight();
                }
            } else {
                checked = true;
            }
            // move em Y, se colidir, volta
            obj.setY(obj.getY() + speedStepY);
            if (obj.collidesWith(tiledLayer, false)) {
                // encosta no bloco colidido:
                // volta ate nao colidir mais
                int stepBack;
                if (speedStepY > 0) {
                    stepBack = -1;
                } else {
                    stepBack = 1;
                }
                obj.setY(obj.getY() + stepBack);
                while (obj.collidesWith(tiledLayer, false)) {
                    obj.setY(obj.getY() + stepBack);
                }
                checked = true;
            }
        }
        return false;
    }
*/