var canvas;
var gl;

var NumVertices  = 36;

var points = [];
var colors = [];

var pointsT = [];
var colorsT = [];

var pointsO = [];
var colorsO = [];

var xAxis = 0;
var yAxis = 1;
var zAxis = 2;

var axis = 0;
var theta = [ 0, 0, 0 ];

var thetaLoc;

window.onload = function init()
{
    canvas = document.getElementById( "gl-canvas" );
    
    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL is not available" ); }
	

	// --------------- Cube --------------------------
	
    colorCube();
	
	// --------------- Tetrahedron -------------------
	
	colorTetra();
	
	// --------------- Octohedron --------------------
	
	colorOcto();


    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 0.1, 0.1, 0.1, 1.0 );
    
    gl.enable(gl.DEPTH_TEST);


    // --------------- Load shaders and initialize attribute buffers

	// --------------- Cube --------------------------
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );
    
    var cBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW );

    var vColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vColor );

    var vBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW );

    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 3, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );

    thetaLoc = gl.getUniformLocation(program, "theta"); 
	
	
	// --------------- Tetrahedron -------------------
	// Create a buffer object, initialize it, and associate it with the
    // associated attribute variable in our vertex shader
    
    var tcBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, tcBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW );
    
    var tvColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( tvColor, 3, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( tvColor );

    var tvBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, tvBuffer);
    gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW );

    var tvPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( tvPosition, 3, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( tvPosition );
	
	
	// --------------- Octohedron --------------------
	// Create a buffer object, initialize it, and associate it with the
    // associated attribute variable in our vertex shader
    
	
    var ocBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, ocBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colorsO), gl.STATIC_DRAW );
    
    var ovColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( ovColor, 3, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( ovColor );

    var ovBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, ovBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(pointsO), gl.STATIC_DRAW );

    var ovPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( ovPosition, 3, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( ovPosition );
	
	
    //event listeners for buttons
	
	var isZ = false;
	var isY = false;
	var isX = false;
    
    document.getElementById( "xButton" ).onclick = function() {
        axis = xAxis;
		isX = !isX;
		document.getElementById('xButton').style.background='#bebfba';
		if(!isX){
			document.getElementById('xButton').style.background='#FFF';
		}
    };
    document.getElementById( "yButton" ).onclick = function() {
        axis = yAxis;
		isY = !isY;
		document.getElementById('yButton').style.background='#bebfba';
		if(!isY){
			document.getElementById('yButton').style.background='#FFF';
		}
    };
    document.getElementById( "zButton" ).onclick = function() {
        axis = zAxis;
		isZ = !isZ;	
		document.getElementById('zButton').style.background='#bebfba';	
		if(!isZ){
			document.getElementById('zButton').style.background='#FFF';
		}	
    };	
   
    render();
}

function render()
{
    gl.clear( gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    theta[axis] += 2.0;
    gl.uniform3fv(thetaLoc, theta);
	
	// Render cube
    gl.drawArrays( gl.TRIANGLES, 0, NumVertices );
	
	// Render tetrahedron
	gl.drawArrays( gl.TRIANGLES, 0, points.length );
	
	// Render sphere
	gl.drawArrays( gl.TRIANGLES, 0, pointsO.length );
    requestAnimFrame( render );
}


// DEFINE CUBE

function colorCube()
{
    quad( 1, 0, 3, 2 );
    quad( 2, 3, 7, 6 );
    quad( 3, 0, 4, 7 );
    quad( 6, 5, 1, 2 );
    quad( 4, 5, 6, 7 );
    quad( 5, 4, 0, 1 );
}

function quad(a, b, c, d) 
{
    var vertices = [
        vec3( -0.25, -0.25,  0.25 ),
        vec3( -0.25,  0.25,  0.25 ),
        vec3(  0.25,  0.25,  0.25 ),
        vec3(  0.25, -0.25,  0.25 ),
        vec3( -0.25, -0.25, -0.25 ),
        vec3( -0.25,  0.25, -0.25 ),
        vec3(  0.25,  0.25, -0.25 ),
        vec3(  0.25, -0.25, -0.25 )
    ];

    var vertexColors = [
        [ 0.9, 0.9, 0.2, 1.0 ],  // oarnge
        [ 1.0, 0.0, 0.0, 1.0 ],  // red
        [ 1.0, 1.0, 0.0, 1.0 ],  // yellow
        [ 0.0, 1.0, 0.0, 1.0 ],  // green
        [ 0.0, 0.0, 1.0, 1.0 ],  // blue
        [ 1.0, 0.0, 1.0, 1.0 ],  // magenta
        [ 1.0, 1.0, 1.0, 1.0 ],  // white
        [ 0.0, 1.0, 1.0, 1.0 ]   // cyan
    ];

    // Partion the quad into two triangles in order for
    // WebGL to be able to render it.      
    // vertex color assigned by the index of the vertex
    
    var indices = [ a, b, c, a, c, d ];

    for ( var i = 0; i < indices.length; ++i ) {
        points.push( vertices[indices[i]] );
        colors.push( vertexColors[indices[i]] );
    
        //for solid colored faces use 
        //colors.push(vertexColors[a]);    
    }
}

// DEFINE TETRAHEDRON

function colorTetra(){
	
	var verticesT = [
        vec3(  0.0000,  0.0000, -0.3500 ),
        vec3(  0.0000,  0.3500,  0.1500 ),
        vec3( -0.3500, -0.1500,  0.1500 ),
        vec3(  0.3500, -0.1500,  0.1500 )
    ];
    
	tetra(verticesT[0], verticesT[1], verticesT[2], verticesT[3]);
}

function makeTetra( a, b, c, color )
{
    // add colors and vertices for one triangle

    var baseColors = [
        vec3(0.7, 0.7, 0.9, 1.0),
        vec3(0.6, 0.8, 0.9, 1.0),
        vec3(0.5, 0.6, 0.9, 1.0),
        vec3(1.0, 1.0, 0.2, 1.0)
    ];

    colors.push( baseColors[color] );
    points.push( a );
    colors.push( baseColors[color] );
    points.push( b );
    colors.push( baseColors[color] );
    points.push( c );
}

function tetra( p, q, r, s )
{
    // tetrahedron with each side using
    // a different color
    
    makeTetra( p, r, q, 0 );
    makeTetra( p, r, s, 1 );
    makeTetra( p, q, s, 2 );
    makeTetra( q, r, s, 3 );
}


// DEFINE OCTOHEDRON

function colorOcto(){
	
    var verticesO = [
		vec3(  0.4000, 0.0000, 0.0000 ),		
		vec3(  0.0000, 0.0000, 0.0000 ),
		vec3(  0.0000, 0.4000, 0.0000 ),
		vec3(  0.4000, 0.4000, 0.0000 ),
		vec3(  0.2000, 0.2000, 0.3000 ),
		vec3(  0.2000, 0.2000, -0.3000 )
    ];
    
	octo(verticesO[0], verticesO[1], verticesO[2], verticesO[3], verticesO[4], verticesO[5]);	
}

function makeOcto( a, b, c, color )
{
    // add colors and vertices for one triangle

    var baseColors = [
		vec3(0.6, 0.6, 0.6, 1.0),
        vec3(0.3, 0.4, 0.9, 1.0),
		vec3(0.9, 0.9, 0.9, 1.0),
    ];

    colorsO.push( baseColors[color] );
    pointsO.push( a );
    colorsO.push( baseColors[color] );
    pointsO.push( b );
    colorsO.push( baseColors[color] );
    pointsO.push( c );
}

function octo( a, b, c, d , e, f)
{
    // tetrahedron with each side using
    // a different color
    
    makeOcto( a, d, e, 0 );
    makeOcto( a, b, e, 1 );
    makeOcto( b, c, e, 0 );
    makeOcto( c, d, e, 1 );
	makeOcto( a, d, f, 1 );
	makeOcto( a, b, f, 2 );
	makeOcto( b, c, f, 1 );
	makeOcto( c, d, f, 2 );
}