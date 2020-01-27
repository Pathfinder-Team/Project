package com.example.pathfinder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.*;
import java.util.*;


public class Setup
{
    ArrayList<Node> points_array = new ArrayList();
    ArrayList<Node> map_points_array = new ArrayList();

    public Setup()
    {

    }
    public void setUpEverythingArrays(SQLiteDatabase db)
    {
        Cursor c = db.rawQuery("select * from map_points",null);
        while (c.moveToNext()) {
            Node edge = new Node(c.getInt(0),
                    c.getString(1),
                    c.getInt(2));
            //Node edge = new Node(result.getInt("current_point_id"), result.getString("point_name"), result.getInt("maps_map_id"));
            map_points_array.add(edge);
        }
        Cursor cd = db.rawQuery("select * from special_points",null);
        while (cd.moveToNext()) {
            Node edge = new Node(cd.getInt(0),
                    cd.getInt(1),
                    cd.getInt(2),
                    cd.getInt(3),
                    cd.getString(4));
            points_array.add(edge);
        }
    }
    public void setupEdges(Graph graph) {
        for (int i = 0; i < map_points_array.size(); i++)
        {
            for(int j = 0; j < points_array.size();j++)
            {
                if (map_points_array.get(i).current_point_id == points_array.get(j).point_from_id) {

                    System.out.println("1: "+points_array.get(j).point_from_id);
                    System.out.print(", 2: "+points_array.get(j).point_to_id);
                    System.out.print(", 3: "+points_array.get(j).point_weight);
                    graph.addEdge(points_array.get(j).point_from_id, points_array.get(j).point_id, points_array.get(j).point_weight);
                }
            }
        }
    }
    public void setUpMap(String currentDest, String finalDest, SQLiteDatabase db) throws IOException {
        Graph graph = new Graph();
        setUpEverythingArrays(db);
        graph = new Graph(map_points_array.size());
        setupEdges(graph);
        graph.findShortestPaths();
    }
}