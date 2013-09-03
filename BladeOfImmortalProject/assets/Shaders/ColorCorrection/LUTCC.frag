uniform sampler2D m_Texture;
uniform sampler2D m_CCMap;
varying vec2 texCoord;
void main() {
    // Set the fragment color for example to gray, alpha 1.0
    vec4 color = texture2D(m_Texture, texCoord);

    gl_FragColor.r = texture2D(m_CCMap, vec2(color.r,0.5f)).r;
    gl_FragColor.g = texture2D(m_CCMap, vec2(color.g,0.5f)).g;
    gl_FragColor.b = texture2D(m_CCMap, vec2(color.b,0.5f)).b;

}

