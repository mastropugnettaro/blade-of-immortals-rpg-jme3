uniform sampler2D m_Texture;
uniform float g_Time;
uniform mat4 m_ColorCorrection;
uniform float m_Contrast;
uniform float m_Brightness;
uniform float m_Gamma;
 
varying vec2 texCoord;
void main() {
    vec4 color = texture2D(m_Texture, texCoord);
 
    gl_FragColor = m_ColorCorrection  * color;
    gl_FragColor.r = pow(gl_FragColor.r, m_Gamma);
    gl_FragColor.g = pow(gl_FragColor.g, m_Gamma);
    gl_FragColor.b = pow(gl_FragColor.b, m_Gamma);
 
    gl_FragColor.rgb = (gl_FragColor.rgb * m_Contrast + m_Brightness);
}