output = 'hello' + input

println me.fool

me.metaClass.talk <<{String what->
    println what;
}

me.talk("love you");