package path;

import java.sql.Timestamp;
import java.util.LinkedList;

public class Node
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
    
    String floorName = "";
    int floorId = 0;
 
    
	int comment_Id;
	String comment_title;
    String comment_text;
    Timestamp created;
    String organisation_name;
    
    public Node()
    {
        
    }
    
    public Node(int floorId, String floorName)
    {
        this.floorId = floorId;
        this.floorName = floorName;
    }
    
    public Node(int source, int destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node(int current_point_id, String point_name, int maps_map_id)
    {
        this.current_point_id = current_point_id;
        this.point_name = point_name;
        this.maps_map_id = maps_map_id;
    }
    public Node(int point_from_id,int point_to_id,int point_weight, String point_direction)
    {
    	this.point_from_id = point_from_id;
        this.point_to_id = point_to_id;
        this.point_weight = point_weight;
        this.point_direction = point_direction;
    }
    
    public Node(int point_id, int point_from_id, int point_to_id, int point_weight, String point_direction)
    {
    	this.point_id = point_id;
    	this.point_from_id = point_from_id;
        this.point_to_id = point_to_id;
        this.point_weight = point_weight;
        this.point_direction = point_direction;
    }
    
    public Node(int comment_Id,String comment_title, String comment_text, Timestamp created,String organisation_name)
    {
    	this.comment_Id = comment_Id;
    	this.comment_title = comment_title;
        this.comment_text = comment_text;
        this.created = created;
        this.organisation_name = organisation_name;
    }

    
    public void removeAll(LinkedList<Node> adj[])
    {
        
    }
}

