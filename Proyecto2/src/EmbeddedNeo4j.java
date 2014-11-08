/**
 * Licensed to Neo Technology under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo Technology licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package proyecto2;

import java.io.File;
import java.util.*;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class EmbeddedNeo4j
{
    private static final String DB_PATH = "target/neo4j-hello-db";

    public String greeting;

    // START SNIPPET: vars
    GraphDatabaseService graphDb;
    Node[] nodo;
    Node firstNode, secondNode, thirdNode, fourthNode, fifthNode;
    Relationship relationship, rlay;
    // END SNIPPET: vars

    // START SNIPPET: createReltype
    private static enum RelTypes implements RelationshipType
    {
        KNOWS
    }
    // END SNIPPET: createReltype

    public static void main( final String[] args )
    {
        Node[] nodo = new Node[10]; 
        EmbeddedNeo4j hello = new EmbeddedNeo4j();
        hello.createDb();
        hello.removeData();
        hello.shutDown();
    }

    void createDb()
    {
        deleteFileOrDirectory( new File( DB_PATH ) );
        // START SNIPPET: startDb
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        registerShutdownHook( graphDb );
        // END SNIPPET: startDb

        // START SNIPPET: transaction
        try ( Transaction tx = graphDb.beginTx() )
        {
            Vector nodosTiempo = new Vector();
            nodosTiempo.add(new String("hola1"));
            nodosTiempo.add(new String("hola2"));
            nodosTiempo.add(new String("hola3"));
            nodosTiempo.add(new String("hola4"));
            nodosTiempo.add(new String("hola5"));
            
            for (int i=0;i<nodosTiempo.size();i++){
                nodo[i] = graphDb.createNode();
                nodo[i].setProperty("tiempo", nodosTiempo.get(i));
                //nodo[i] = nodosTiempo.get(i);
            }
            // Database operations go here
            // END SNIPPET: transaction
            // START SNIPPET: addData
            firstNode = graphDb.createNode();
            firstNode.setProperty( "message", "Hello, " );
            secondNode = graphDb.createNode();
            secondNode.setProperty( "message", "World!" );
            thirdNode = graphDb.createNode();
            thirdNode.setProperty("tiempo", "34");
            fourthNode = graphDb.createNode();
            fourthNode.setProperty("tiempo","18");
            fifthNode = graphDb.createNode();
            fifthNode.setProperty("tiempo", nodosTiempo.get(nodosTiempo.size()-1));

            relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
            relationship.setProperty( "message", "brave Neo4j " );
            // END SNIPPET: addData
            
            //rlay = thirdNode.createRelationshipTo( fourthNode, RelTypes.KNOWS );
            //rlay.setProperty( "tiempo", "Data Rec." );

            // START SNIPPET: readData
            System.out.print( firstNode.getProperty( "message" ) );
            System.out.print( relationship.getProperty( "message" ) );
            System.out.print( secondNode.getProperty( "message" ) );
            System.out.print( thirdNode.getProperty( "tiempo" ) );
            System.out.print( fourthNode.getProperty( "tiempo" ) );  
            System.out.print( fifthNode.getProperty( "tiempo" ) );
            
            for (int i=0;i<nodosTiempo.size();i++){
                System.out.print(nodo[i].getProperty("tiempo"));
            }
            
            
            // END SNIPPET: readData

            greeting = ( (String) firstNode.getProperty( "message" ) )
                       + ( (String) relationship.getProperty( "message" ) )
                       + ( (String) secondNode.getProperty( "message" ) )
                       + ( (String) thirdNode.getProperty( "tiempo" ) ) 
                       + ( (String) fourthNode.getProperty( "tiempo" ) )
                       + ( (String) fifthNode.getProperty( "tiempo" ) );       
            
            // START SNIPPET: transaction
            tx.success();
        }
        // END SNIPPET: transaction
    }

    void removeData()
    {
        try ( Transaction tx = graphDb.beginTx() )
        {
            // START SNIPPET: removingData
            // let's remove the data
            firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
            firstNode.delete();
            secondNode.delete();
            // END SNIPPET: removingData

            tx.success();
        }
    }

    void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database ..." );
        // START SNIPPET: shutdownServer
        graphDb.shutdown();
        // END SNIPPET: shutdownServer
    }

    // START SNIPPET: shutdownHook
    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }
    // END SNIPPET: shutdownHook

    private static void deleteFileOrDirectory( File file )
    {
        if ( file.exists() )
        {
            if ( file.isDirectory() )
            {
                for ( File child : file.listFiles() )
                {
                    deleteFileOrDirectory( child );
                }
            }
            file.delete();
        }
    }
}