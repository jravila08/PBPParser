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

/**
 * I know a singleton is probably overkill here but oh well
 * @author jravi
 */
public class VariableHolder {
    
    private static VariableHolder myInstance;
    
    //path to the game folder
    private String myGamePath;
    
    private VariableHolder()
    {
        myGamePath = System.getProperty("file.path","/home/");
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
    
}
