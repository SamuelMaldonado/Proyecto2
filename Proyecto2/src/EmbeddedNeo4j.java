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
/* 
 * @author Brandon Mendez - 13087
 * @author Javier Bucaro - 13033
 * @author Samuel Maldonado - 13153
 * @author Kuk Ho Chung - 13279
*/
package proyectoprueba;

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

    public String greeting,nodesValues="";

    // START SNIPPET: vars
    GraphDatabaseService graphDb;
    Node nodo1,nodo2,nodo3,nodo4,nodo5,nodo6,nodo7,nodo8,nodo9,nodo10,nodo11,
         nodo12,nodo13,nodo14,nodo15,nodo16,nodo17,nodo18,nodo19,nodo20,nodo21,
         nodo22,nodo23,nodo24,nodo25;
    Relationship relationship;
    HaceMatriz Obj2;
    // END SNIPPET: vars

    // START SNIPPET: createReltype
    private static enum RelTypes implements RelationshipType
    {
        USERS_REFERENCE, USER, KNOWS,
    }
    // END SNIPPET: createReltype
    
    public void inicio() 
    { 
        HaceMatriz Obj2 = new HaceMatriz();
        Obj2.escribeEnTexto();
        Obj2.haceMatrizAleatorios();
        EmbeddedNeo4j hello = new EmbeddedNeo4j();
        hello.createDb();
        hello.removeData();
        hello.shutDown();
    }
    
    
    
    public String createDb()
    {
        deleteFileOrDirectory( new File( DB_PATH ) );
        // START SNIPPET: startDb
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        registerShutdownHook( graphDb );
        // END SNIPPET: startDb
        ObtenerDatos prueba1 = new ObtenerDatos();
        // START SNIPPET: transaction
        try ( Transaction tx = graphDb.beginTx() )
        {
            
            // Database operations go here
            // END SNIPPET: transaction
            // START SNIPPET: addData
            nodo1 = graphDb.createNode();
            nodo1.setProperty( "01/10/2014", prueba1.LecturaMatriz(0)+" ");
            nodo2 = graphDb.createNode();
            nodo2.setProperty( "02/10/2014", prueba1.LecturaMatriz(1)+" ");
            nodo3 = graphDb.createNode();
            nodo3.setProperty( "03/10/2014", prueba1.LecturaMatriz(2)+" ");
            nodo4 = graphDb.createNode();
            nodo4.setProperty( "04/10/2014", prueba1.LecturaMatriz(3)+" ");
            nodo5 = graphDb.createNode();
            nodo5.setProperty( "05/10/2014", prueba1.LecturaMatriz(4)+" ");
            nodo6 = graphDb.createNode();
            nodo6.setProperty( "06/10/2014", prueba1.LecturaMatriz(5)+" ");
            nodo7 = graphDb.createNode();
            nodo7.setProperty( "07/10/2014", prueba1.LecturaMatriz(6)+" ");
            nodo8 = graphDb.createNode();
            nodo8.setProperty( "08/10/2014", prueba1.LecturaMatriz(7)+" ");            
            nodo9 = graphDb.createNode();
            nodo9.setProperty( "09/10/2014", prueba1.LecturaMatriz(8)+" ");
            nodo10 = graphDb.createNode();
            nodo10.setProperty( "10/10/2014", prueba1.LecturaMatriz(9)+" ");
            nodo11 = graphDb.createNode();
            nodo11.setProperty( "11/10/2014", prueba1.LecturaMatriz(10)+" ");
            nodo12 = graphDb.createNode();
            nodo12.setProperty( "12/10/2014", prueba1.LecturaMatriz(11)+" ");
            nodo13 = graphDb.createNode();
            nodo13.setProperty( "13/10/2014", prueba1.LecturaMatriz(12)+" ");
            nodo14 = graphDb.createNode();
            nodo14.setProperty( "14/10/2014", prueba1.LecturaMatriz(13)+" ");
            nodo15 = graphDb.createNode();
            nodo15.setProperty( "15/10/2014", prueba1.LecturaMatriz(14)+" ");
            nodo16 = graphDb.createNode();
            nodo16.setProperty( "16/10/2014", prueba1.LecturaMatriz(15)+" ");  
            nodo17 = graphDb.createNode();
            nodo17.setProperty( "17/10/2014", prueba1.LecturaMatriz(16)+" ");            
            nodo18 = graphDb.createNode();
            nodo18.setProperty( "18/10/2014", prueba1.LecturaMatriz(17)+" ");
            nodo19 = graphDb.createNode();
            nodo19.setProperty( "19/10/2014", prueba1.LecturaMatriz(18)+" ");
            nodo20 = graphDb.createNode();
            nodo20.setProperty( "20/10/2014", prueba1.LecturaMatriz(19)+" ");
            nodo21 = graphDb.createNode();
            nodo21.setProperty( "21/10/2014", prueba1.LecturaMatriz(20)+" ");
            nodo22 = graphDb.createNode();
            nodo22.setProperty( "22/10/2014", prueba1.LecturaMatriz(21)+" ");
            nodo23 = graphDb.createNode();
            nodo23.setProperty( "23/10/2014", prueba1.LecturaMatriz(22)+" ");
            nodo24 = graphDb.createNode();
            nodo24.setProperty( "24/10/2014", prueba1.LecturaMatriz(23)+" ");
            nodo25 = graphDb.createNode();
            nodo25.setProperty( "25/10/2014", prueba1.LecturaMatriz(24)+" ");              
            
            relationship = nodo1.createRelationshipTo( nodo2, RelTypes.KNOWS );
            relationship.setProperty( "message", "Neo4j funciona! Lista de segundos de sueño: " );
            // END SNIPPET: addData

            // START SNIPPET: readData
            
            System.out.print( relationship.getProperty( "message" ) );
            System.out.print( nodo1.getProperty( "01/10/2014" ) );
            System.out.print( nodo2.getProperty( "02/10/2014" ) );
            System.out.print( nodo3.getProperty( "03/10/2014" ) ); 
            System.out.print( nodo4.getProperty( "04/10/2014" ) );           
            System.out.print( nodo5.getProperty( "05/10/2014" ) );            
            System.out.print( nodo6.getProperty( "06/10/2014" ) );
            System.out.print( nodo7.getProperty( "07/10/2014" ) );
            System.out.print( nodo8.getProperty( "08/10/2014" ) );
            System.out.print( nodo9.getProperty( "09/10/2014" ) ); 
            System.out.print( nodo10.getProperty( "10/10/2014" ) );           
            System.out.print( nodo11.getProperty( "11/10/2014" ) );            
            System.out.print( nodo12.getProperty( "12/10/2014" ) );
            System.out.print( nodo13.getProperty( "13/10/2014" ) );           
            System.out.print( nodo14.getProperty( "14/10/2014" ) );
            System.out.print( nodo15.getProperty( "15/10/2014" ) ); 
            System.out.print( nodo16.getProperty( "16/10/2014" ) );           
            System.out.print( nodo17.getProperty( "17/10/2014" ) );            
            System.out.print( nodo18.getProperty( "18/10/2014" ) );
            System.out.print( nodo19.getProperty( "19/10/2014" ) );            
            System.out.print( nodo20.getProperty( "20/10/2014" ) );
            System.out.print( nodo21.getProperty( "21/10/2014" ) ); 
            System.out.print( nodo22.getProperty( "22/10/2014" ) );           
            System.out.print( nodo23.getProperty( "23/10/2014" ) );            
            System.out.print( nodo24.getProperty( "24/10/2014" ) );
            System.out.print( nodo25.getProperty( "25/10/2014" ) );             
            
            
            
                        nodesValues=(String) nodo1.getProperty( "01/10/2014" )+" " ;
                        nodesValues+=(String) nodo2.getProperty( "02/10/2014" )+" " ;
                        nodesValues+=(String) nodo3.getProperty( "03/10/2014" )+" " ;
                        nodesValues+=(String) nodo4.getProperty( "04/10/2014" )+" " ;
                        nodesValues+=(String) nodo5.getProperty( "05/10/2014" )+" " ;
                        nodesValues+=(String) nodo6.getProperty( "06/10/2014" )+" " ;
                        nodesValues+=(String) nodo7.getProperty( "07/10/2014" )+" " ;
                        nodesValues+=(String) nodo8.getProperty( "08/10/2014" )+" " ;
                        nodesValues+=(String) nodo9.getProperty( "09/10/2014" )+" " ;
                        nodesValues+=(String) nodo10.getProperty( "10/10/2014" )+" " ;
                        nodesValues+=(String) nodo11.getProperty( "11/10/2014" )+" " ;
                        nodesValues+=(String) nodo12.getProperty( "12/10/2014" )+" " ;
                        nodesValues+=(String) nodo13.getProperty( "13/10/2014" )+" " ;
                        nodesValues+=(String) nodo14.getProperty( "14/10/2014" )+" " ;
                        nodesValues+=(String) nodo15.getProperty( "15/10/2014" )+" " ;
                        nodesValues+=(String) nodo16.getProperty( "16/10/2014" )+" " ;
                        nodesValues+=(String) nodo17.getProperty( "17/10/2014" )+" " ;
                        nodesValues+=(String) nodo18.getProperty( "18/10/2014" )+" " ;
                        nodesValues+=(String) nodo19.getProperty( "19/10/2014" )+" " ;
                        nodesValues+=(String) nodo20.getProperty( "20/10/2014" )+" " ;
                        nodesValues+=(String) nodo21.getProperty( "21/10/2014" )+" " ;
                        nodesValues+=(String) nodo22.getProperty( "22/10/2014" )+" " ;
                        nodesValues+=(String) nodo23.getProperty( "23/10/2014" )+" " ;
                        nodesValues+=(String) nodo24.getProperty( "24/10/2014" )+" " ;
                        nodesValues+=(String) nodo25.getProperty( "25/10/2014" );

            // END SNIPPET: readData

            greeting = ( (String) nodo1.getProperty( "01/10/2014" ) )
                       + ( (String) relationship.getProperty( "message" ) )
                       + ( (String) nodo2.getProperty( "02/10/2014" ) )
                       + ( (String) nodo3.getProperty( "03/10/2014" ) )
                       + ( (String) nodo4.getProperty( "04/10/2014" ) )
                       + ( (String) nodo5.getProperty( "05/10/2014" ) )
                       + ( (String) nodo6.getProperty( "06/10/2014" ) )
                       + ( (String) nodo7.getProperty( "07/10/2014" ) )
                       + ( (String) nodo8.getProperty( "08/10/2014" ) )
                       + ( (String) nodo9.getProperty( "09/10/2014" ) )
                       + ( (String) nodo10.getProperty( "10/10/2014" ) )
                       + ( (String) nodo11.getProperty( "11/10/2014" ) )
                       + ( (String) nodo12.getProperty( "12/10/2014" ) )
                       + ( (String) nodo13.getProperty( "13/10/2014" ) )
                       + ( (String) nodo14.getProperty( "14/10/2014" ) )
                       + ( (String) nodo15.getProperty( "15/10/2014" ) )
                       + ( (String) nodo16.getProperty( "16/10/2014" ) )
                       + ( (String) nodo17.getProperty( "17/10/2014" ) )
                       + ( (String) nodo18.getProperty( "18/10/2014" ) )
                       + ( (String) nodo19.getProperty( "19/10/2014" ) )
                       + ( (String) nodo20.getProperty( "20/10/2014" ) )
                       + ( (String) nodo21.getProperty( "21/10/2014" ) )
                       + ( (String) nodo22.getProperty( "22/10/2014" ) )
                       + ( (String) nodo23.getProperty( "23/10/2014" ) )
                       + ( (String) nodo24.getProperty( "24/10/2014" ) )
                       + ( (String) nodo25.getProperty( "25/10/2014" ) );                    
            
            // START SNIPPET: transaction
            tx.success();
            
            return nodesValues;
        }
        // END SNIPPET: transaction
    }

    void removeData()
    {
        try ( Transaction tx = graphDb.beginTx() )
        {
            // START SNIPPET: removingData
            // let's remove the data
            nodo1.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
            nodo1.delete();
            nodo2.delete();
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
    
    public String getnodesValues(){
        return nodesValues;
    }
    
}
