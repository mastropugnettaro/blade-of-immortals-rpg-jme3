package script
/*
// The GroovyScriptEngine needs to know the root directories of your code
//String[] roots = ['./script']
String[] roots = ['./src/script']
def engine = new GroovyScriptEngine(roots)
 
// Create a binding of any variables we want to pass to the script
def binding = [input:'world',me:this] as Binding
 
fool="who"

// Run the script
engine.run('TestScript.groovy', binding)
 
// Get the result
//assert binding.getVariable('output') == 'hello world'

//println binding.getVariable('output')

this.talk "love you more!"

this.properties.each(){it->
    println it; 
}
*/
import com.jme3.math.Vector3f;

class Enemy{
    Vector3f pos;
    
    Enemy(Vector3f pos){
        this.pos = pos;
    }
    
    String toString(){
        return pos;
    }
}

e1=new Enemy(new Vector3f(1,1,1));
e2=new Enemy(new Vector3f(2,2,2));
e3=new Enemy(new Vector3f(4,4,4));
v0 = new Vector3f(0,0,0);
(1..3).findAll { i ->
    str = "e"+i
  this."$str".pos.distance(v0)>2;
}
.each(){
    println it;
}

