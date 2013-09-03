uniform sampler2D m_Texture;
uniform sampler2D m_CCMap;
varying vec2 texCoord;

void main() {
    // Set the fragment color for example to gray, alpha 1.0
    vec4 color = texture2D(m_Texture, texCoord);
    float v = color.g;
    float u= (0.5+ floor (color.b * 15) * 16 + floor (color.r * 15))/256;
    vec4 newColor = texture2D(m_CCMap, vec2(u,v));
    gl_FragColor = color * 0.1 + newColor * 0.9;
    gl_FragColor.a = color.a;

    //gl_FragColor = vec4(1,1,0.4,1);
}

