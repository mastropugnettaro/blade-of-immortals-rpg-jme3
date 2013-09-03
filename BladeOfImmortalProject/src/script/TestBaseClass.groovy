package script;
import org.codehaus.groovy.control.CompilerConfiguration;

class ScriptBaseTest {

  void extend_groovy_script() {
    def compiler = new CompilerConfiguration()
    compiler.setScriptBaseClass("ScriptBaseTestScript")

    def shell = new GroovyShell(this.class.classLoader, new Binding(), compiler)
    shell.evaluate("foo()");
  }
}

a = new ScriptBaseTest();
a.extend_groovy_script();