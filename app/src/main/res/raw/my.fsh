precision highp float;

uniform vec3                iResolution;
uniform float               iGlobalTime;
uniform sampler2D           iChannel0;
uniform sampler2D           iChannel1;
varying vec2                texCoord;


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    vec2 uv = fragCoord.xy / iResolution.xy;
    // float x = sin(iGlobalTime);
    float x = mod(iGlobalTime, 2.0) / 2.0;
    if (uv.x < x)
    {
        fragColor = texture2D(iChannel0, uv);
    }
    else
    {
        fragColor = texture2D(iChannel1, uv);
    }
}

void main() {
	mainImage(gl_FragColor, texCoord * iResolution.xy);
}