package com.example.pathfinder;

import java.io.Serializable;

public class Node implements Serializable
{

    int source;
    int destination;
    int weight;

    int current_point_id;
    String point_name;
    int maps_map_id;

    int point_id;
    int point_from_id;
    int point_to_id;
    int point_weight;
    String point_direction;

    int fromPointId;
    int toPointId;
    String pointDirection;

    String fromPointName;
    String toPointName;
    String pointDirectionName;

    int point;
    String name;

    public Node()
    {

    }
    public Node(int point, String name)
    {
        this.point = point;
        this.name = name;
    }

    public Node(int source, int destination)
    {
        this.source = source;
        this.destination = destination;
    }
    public Node(int source, int destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public Node(String fromPointName, String toPointName,String pointDirectionName)
    {
        this.fromPointName = fromPointName;
        this.toPointName = toPointName;
        this.pointDirectionName = pointDirectionName;
    }
    public Node(int fromPointId, int toPointId, String pointDirection)
    {
        this.fromPointId = fromPointId;
        this.pointDirection = pointDirection;
        this.toPointId = toPointId;
    }
    public Node(int current_point_id, String point_name, int maps_map_id)
    {
        this.current_point_id = current_point_id;
        this.point_name = point_name;
        this.maps_map_id = maps_map_id;
    }
    public Node(int point_id, int point_from_id, int point_to_id, int point_weight, String point_direction)
    {
        this.point_id = point_id;
        this.point_from_id = point_from_id;
        this.point_to_id = point_to_id;
        this.point_weight = point_weight;
        this.point_direction = point_direction;
    }
    public Node(int point_from_id, int point_to_id, int point_weight, String point_direction)
    {
        this.point_from_id = point_from_id;
        this.point_to_id = point_to_id;
        this.point_weight = point_weight;
        this.point_direction = point_direction;
    }
}

