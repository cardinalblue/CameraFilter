precision highp float;

uniform vec3                iResolution;
uniform float               iGlobalTime;
uniform sampler2D           iChannel0;
varying vec2                texCoord;

float GetTexture(float x, float y)
{
    return texture2D(iChannel0, vec2(x, y) / iResolution.xy).x;
}

void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    float threshold = 0.2;
    float x = fragCoord.x;
    float y = fragCoord.y;

    float xValue = -GetTexture(x-1.0, y-1.0) - 2.0*GetTexture(x-1.0, y) - GetTexture(x-1.0, y+1.0)
        + GetTexture(x+1.0, y-1.0) + 2.0*GetTexture(x+1.0, y) + GetTexture(x+1.0, y+1.0);
    float yValue = GetTexture(x-1.0, y-1.0) + 2.0*GetTexture(x, y-1.0) + GetTexture(x+1.0, y-1.0)
        - GetTexture(x-1.0, y+1.0) - 2.0*GetTexture(x, y+1.0) - GetTexture(x+1.0, y+1.0);

    if(length(vec2(xValue, yValue)) > threshold)
    {
        fragColor = vec4(0);
    }
    else
    {
        vec2 uv = vec2(x, y) / iResolution.xy;
    	vec4 currentPixel = texture2D(iChannel0, uv);
        fragColor = currentPixel;
    }
}

void main() {
	mainImage(gl_FragColor, texCoord*iResolution.xy);
}