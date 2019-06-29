package map;

import drone.Drone;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class TileMap {

    private Tile[][] tileMap;
    private int width, height;
    private ArrayList<Drone> drones;

    public TileMap(int width, int heigth){
        this.tileMap = new Tile[heigth][width];
        this.width = width;
        this.height = heigth;
        this.drones = new ArrayList<>();
    }

    public TileMap(Tile[][] tiles){
        this.tileMap = tiles;
        this.width = tiles[1].length;
        this.height = tiles.length;
        this.drones = new ArrayList<>();
    }

    public void setTile(Tile input, int x, int y){
        this.tileMap[y][x] = input;
    }

    public void setTileMap(Tile[][] tileMap) {
        assert tileMap[1].length != this.tileMap[1].length || tileMap.length != this.tileMap.length :
                "Given tilemap has to be the same size as the map size. Expected Size: " + width + "x" + height
                + ". Actual size: " + tileMap[1].length + "x" + tileMap.length;

        this.tileMap = tileMap;
    }

    /**
     *
     * @param x the x value of the tile
     * @param y the y value of the tile
     * @return the tile that is present at the given x and y coordinates
     */
    public Tile getTile(int x, int y){
        return this.tileMap[y][x];
    }

    /**
     *
     * @param searchedTile the tile that is searched for
     * @return a true or false value based on whether the tale is present or not
     */
    public boolean containsTile(Tile searchedTile){
        for (Tile[] tiles : this.tileMap){
            for (Tile tile : tiles){
                if (tile.equals(searchedTile)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param resourceType of resource that is searched
     * @return true or false depending on whether the resource is present or not
     */
    public boolean containsResource(ResourceType resourceType){
        for (Tile[] tiles : this.tileMap){
            for (Tile tile : tiles){
                if (tile.getResource().equals(resourceType)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @return the 2d tile map as a String object
     */
    public String toString(){
        StringBuilder map = new StringBuilder();

        for (Tile[] tiles : this.tileMap) {
            for (Tile tile : tiles){
                map.append(tile);
                map.append(", ");
            }
            map.append("\n");
        }

        return map.toString();
    }

    /**
     *
     * @return the width of the map
     */
    public int getWidth(){
        return this.width;
    }

    /**
     *
     * @return the height of the map
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * helper method of the actual recursive function
     * @param resourceType the resource type you are looking for
     * @param startingX the x coordinate you want to start your search on
     * @param startingY the y coordinate you want to start your search on
     * @return an x and y value in a Point2D object of where the closest resource is. Returns null if there are none of the resource present
     */
    public Point2D getClosestResource(ResourceType resourceType, int startingX, int startingY){
        ArrayList<Point2D> points = new ArrayList<>();
        Point2D currentPoint = new Point2D.Double(startingX, startingY);

        if (this.tileMap[(int)currentPoint.getY()][(int)currentPoint.getX()].getResource().equals(resourceType)) {
            return currentPoint;
        }

        int[][] markedMap = new int[height][width];

        points.add(currentPoint);
        markedMap[startingY][startingX] = 1;
        return getClosestResource(resourceType, points, markedMap);
    }

    /**
     *
     * @param resourceType the resource type you are looking for
     * @param point2DS a list of coordinates where the surrounding tiles need to be searched for the given resource
     * @param markedMap a 2d array that helps the function with coordinates already looked at. Value of coordinate is 1 if already looked at
     * @return an x and y value of the coordinates in a point2d object of the searched resource. returns null if the resource is not present
     */
    private Point2D getClosestResource(ResourceType resourceType, ArrayList<Point2D> point2DS, int[][] markedMap){
        ArrayList<Point2D> newPoints = new ArrayList<>();

        for (Point2D point2D : point2DS){

            int x = (int)point2D.getX();
            int y = (int)point2D.getY();

            if (x - 1 >= 0 && markedMap[y][x-1] == 0){                      //left tile
                if (tileMap[y][x-1].getResource().equals(resourceType)){
                    return new Point2D.Double(x-1, y);
                }

                newPoints.add(new Point2D.Double(x-1, y));
                markedMap[y][x-1] = 1;
            }

            if (x + 1 <= width-1 && markedMap[y][x+1] == 0){                //right tile
                if (tileMap[y][x+1].getResource().equals(resourceType)){
                    return new Point2D.Double(x+1, y);
                }

                newPoints.add(new Point2D.Double(x+1, y));
                markedMap[y][x+1] = 1;
            }

            if (y - 1 >= 0 && markedMap[y-1][x] == 0){                      //upper tile
                if (tileMap[y-1][x].getResource().equals(resourceType)){
                    return new Point2D.Double(x, y-1);
                }

                newPoints.add(new Point2D.Double(x, y-1));
                markedMap[y-1][x] = 1;
            }

            if (y + 1 <= height-1 && markedMap[y+1][x] == 0){               //lower tile
                if (tileMap[y+1][x].getResource().equals(resourceType)){
                    return new Point2D.Double(x, y+1);
                }

                newPoints.add(new Point2D.Double(x, y+1));
                markedMap[y+1][x] = 1;
            }
        }

        if (!newPoints.isEmpty()) {     //if there are any tiles left on the map, do this method again on the remaining tiles
            return getClosestResource(resourceType, newPoints, markedMap);
        }

        return null;

    }

    /**
     *
     * @param resourceType the resource type you are looking for
     * @return the amount of tiles in the array that have this resource
     */
    public int getResourceTileAmount(ResourceType resourceType){
        int amount = 0;
        for (Tile[] tiles : tileMap){
            for (Tile tile: tiles){
                if (tile.getResource().equals(resourceType)){
                    amount++;
                }
            }
        }
        return amount;
    }

    /**
     * Sets all resources of the map to ENERGY
     */
    public void reset(){
        Arrays.setAll(this.tileMap, x -> {
            Arrays.setAll(this.tileMap[x], y -> new Tile());
            return this.tileMap[x];
        });
    }

    /**
     *
     * @param resourceType the resource type you want
     *                     to scatter across the map
     * @param density the chance of a resource appearing on a
     *                tile, with 0 being the lowest and 1 being the highest
     *
     */
    public void scatterResources(ResourceType resourceType, double density){
        for (Tile[] tiles : this.tileMap){
            for (Tile tile : tiles){
                if (tile.getResource().equals(ResourceType.ENERGY) && Math.random() < density){
                    tile.setResource(resourceType);
                }
            }
        }
    }

    /**
     *
     * @param startX the x coordinate where to start counting
     * @param endX the x coordinate where to end counting
     * @param y the vertical cordinate the function needs to search
     * @return an array of tiles sorted in the order they were encountered from the startX until the endX
     */
    public Tile[] getHorizontalTiles(int startX, int endX, int y){
        ArrayList<Tile> tiles = new ArrayList<>();
        if (startX < endX){
            for (int i = startX; i <= endX; i++){
                tiles.add(tileMap[y][i]);
            }
        }else{
            for (int i = startX; i >= endX; i--){
                tiles.add(tileMap[y][i]);
            }
        }
        return (Tile[]) tiles.toArray();
    }

    /**
     *
     * @param y the vertical line on which the function needs to search
     * @return an array of tiles from left to right from the y value of the 2d array
     */
    public Tile[] getHorizontalTiles(int y){
        ArrayList<Tile> tiles = new ArrayList<>(Arrays.asList(tileMap[y]).subList(0, this.width));

        return (Tile[]) tiles.toArray();
    }

    /**
     *
     * @param startY the y coordinate on which the function needs to start adding
     * @param endY the y coordinate on which the function needs to end adding
     * @param x the x coordinate of the line on which will be added
     * @return an array of tiles encountered in order from the starting y up until the ending y
     */
    public Tile[] getVerticalTiles(int startY, int endY, int x){
        ArrayList<Tile> tiles = new ArrayList<>();
        if (startY < endY){
            for (int i = startY; i <= endY; i++){
                tiles.add(tileMap[i][x]);
            }
        }else {
            for (int i = startY; i >= endY; i--){
                tiles.add(tileMap[i][x]);
            }
        }
        return (Tile[]) tiles.toArray();
    }

    /**
     *
     * @param x the x value on which the function must search
     * @return an array of tiles encountered in order from the top to the bottom of the 2d array
     */
    public Tile[] getVerticalTiles(int x){
        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < this.height; i++){
            tiles.add(tileMap[i][x]);
        }

        return (Tile[]) tiles.toArray();
    }

    public void fillNullTiles(){
       for (Tile[] tiles : tileMap){
           for (Tile tile : tiles){
               if (tile == null){
                   tile = new Tile(ResourceType.ENERGY, 0);
               }
           }
       }
    }

    public int getDroneAmount(){
        return drones.size();
    }

}
