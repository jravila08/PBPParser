/*
 * Copyright 2017 jravi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package filehandler;

import java.util.LinkedList;
import java.util.Random;

/**
 * I know a singleton is probably overkill here but oh well
 * @author jravi
 */
public class VariableHolder {
	
	//a csv file of d20 rolls to be used as random numbers
	private static final String d20FileName = "d20rolls.txt";
    
    private static VariableHolder myInstance;
    
    //path to the game folder
    private String myGamePath;
    
    //This will hold the list of rolls to be used as random numbers
    private LinkedList<Integer> d20List;
    
    private VariableHolder()
    {
        myGamePath = System.getProperty("file.path","/home/");
        d20List = new LinkedList<Integer>();
        this.populateD20List();
    }
    
    
    private void populateD20List() {
		// TODO Auto-generated method stub
	}

	public static VariableHolder getInstance() {
        if ( myInstance == null )
        {
            myInstance = new VariableHolder();
        }
        return myInstance;
    }
    
    /**
     * 
     * @return the path to the game directory (passed to the prog as -Dfile.path=[]) 
     */
    public String getGamePath()
    {
        return myGamePath;
    }
    
    /**
     * get a d20 roll from the list.  If there are none left gen a random number
     * @return a random number 1 - 20
     */
    public int popD20()
    {
    	if ( d20List.size() > 0 )
    	{
    		return d20List.pop().intValue();
    	}
    	Random r = new Random();
    	return r.nextInt(20)+1;
    }
    
}
