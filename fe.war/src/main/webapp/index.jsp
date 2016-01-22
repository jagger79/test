<!--
    JBoss, Home of Professional Open Source
    Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
    contributors by the @authors tag. See the copyright.txt in the
    distribution for a full listing of individual contributors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
-->
<!DOCTYPE html>
<html>
<head>
    <title>Poznámkovník</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <!-- Set the tab, home page, shortcut icons -->
    <link rel="Shortcut Icon" href="img/aerogear_icon_16x.png">
    <link rel="apple-touch-icon" href="img/aerogear_avatar_48px.png"/>

    <!-- For minification, comment out this link -->
    <!-- Shared styles -->
    <link rel="stylesheet" href="css/screen.css"/>
<style>
/* Any demo specific styling needed for this tutorial only */

.box {
    position: absolute;
    height: 200px;
    width: 200px;
	background-color: #ddd;
	border: 1px #eee;
	padding: 5px;
}
#container {
    position: relative;
    padding: 10px;
    height: 300px;
    width: 450px;
}
* {
    outline: none !important;
}
body {
    margin: 0;
    padding: 2em;
    font-family: Lucida Sans,Lucida Grande,Arial !important;
    font-size: 13px !important;
    background: white;
    color: #333;
}
button {
	-webkit-transition: background-color 0.2s linear;
	border-radius: 14px 100px 10px 70px;
	-moz-border-radius: 4px 4px 4px 4px;
	-moz-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15);
	background-color: #E4F2FF;
	background-image: url("//ajax.googleapis.com/ajax/libs/dojo/1.7.4/dijit/themes/claro/form/images/button.png");
	background-position: center top;
	background-repeat: repeat-x;
	border: 1px solid #769DC0;
	padding: 2px 8px 4px;
	font-size:1em;
	z-index: 99;
}
button:hover {
	background-color: #AFD9FF;
	color: #000000;
}

h1 {
	font-size:1.5em;
}
.innerBox {
    margin: 5%;
	padding: 5px;
	background-color: white;
}
.contentBox {
	background-color: white;
    position: absolute;
    width: 200px;
	border: solid 1px #99c;
	margin: 5px;

    -moz-box-shadow: 10px 10px 5px #888;
    -webkit-box-shadow: 2px 3px 5px #888;
    box-shadow: 0px 10px 200px 100px gray;
    border-radius: 100px 10px 100px 10px;
}
.innerBox1 {
    margin: 5%;
	padding: 5px;
	background-color: white;
    box-shadow: 10px 10px 100px 15px black;
}

.break
{
float:none;
clear:both;
}
.box1 {
z-index:50;
background-color:blue;
}
.box2 {
z-index:51;
background-color:red;
}
.boxLower {
z-index:1;
}
.contentBoxLower {
	background-color: white;
    position: absolute;
    width: 200px;
	border: solid 1px #99c;
	margin: 5px;

    -moz-box-shadow: 10px 10px 5px #888;
    -webkit-box-shadow: 2px 3px 5px #888;
    box-shadow: 0px 10px 50px 20px gray;
}
.boxDown {
z-index:0;
}
.contentBoxDown {
	background-color: white;
    position: absolute;
    top: 0px;
    left: 400px;
    width: 200px;
	border: solid 1px #99c;
	margin: 5px;

    -moz-box-shadow: 10px 10px 5px #888;
    -webkit-box-shadow: 2px 3px 5px #888;
    box-shadow: 1px 1px 10px 0px gray;
}

</style>
    <!-- Set viewport scaling and zoom features -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%
    // compressed version
//    <script type="text/javascript" src="js/libs/modernizr-2.6.2.min.js"></script>
    //http://code.jquery.com/jquery-2.1.1.min.js
    request.getServletPath();
    %>
    <script src="<%=request.getServletContext().getContextPath()%>/js/libs/jquery-2.1.1.js"></script> 
</head>

<body>
<button id="newWindow">New window</button>
<button id="swapButton">Swap</button>
 
<div class="container" id="container">
    <div id="contentDown" class="contentBoxDown boxDown">
        <div class="innerBox">1: Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate</div>
    </div>
    <div id="content1" class="contentBox box1" style="top: 0; left: 0">
        <div class="innerBox1">1: Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</div>
    </div>
    <div id="content2" class="contentBox box2" style="top: 0; left: 250px">
        <div class="innerBox1">2: Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</div>
    </div>
</div>

		<!-- load dojo -->
    	<script src="//ajax.googleapis.com/ajax/libs/dojo/1.9.3/dojo/dojo.js"></script>
		<script>
		
			require(["dojo/_base/fx", "dojo/fx", "dojo/fx/easing", "dojo/dom-style",
			         "dojo/dom", "dojo/aspect", "dojo/on", "dojo/domReady!"],
			         function(baseFx, fx, easing, domStyle, dom, aspect, on) {
				
				var mainNodes = new Array();
				// define a function to return the animation which
				// swaps the positions of 2 nodes
				function swapAnim(node1, node2) {
					var myArray = new Array();
					for (var entry in mainNodes) {
					//mainNodes.foreach(function(entry) {
						var node3 = dom.byId(mainNodes[entry]);
						myArray[myArray.length] =
							fx.slideTo({
								duration: 1200,
								node: node3,
								left: Math.random()*1000,
								top:  Math.random()*450
							})
					};
					myArray[myArray.length] = fx.slideTo({
							duration: 1200,
							node: node2,
							left: Math.random()*1000,
							top: Math.random()*450
					});
					myArray[myArray.length] = fx.slideTo({
							duration: 1200,
							node: node1,
							left: Math.random()*1000,
							top: Math.random()*450
					});
					return moveNodes = fx.combine(myArray);
				}

				var originalOrder = true; // track which order our content nodes are in

				var swapButton = dom.byId("swapButton"),
				c1 = originalOrder ? dom.byId("content1") : dom.byId("content2"),
				c2 = originalOrder ? dom.byId("content2") : dom.byId("content1"),
				container = dom.byId("container"),
				newWindow = dom.byId("newWindow");

				on(newWindow, "click", function(evt) {
					var element = document.createElement("div");
					element.id = "test"+Math.random()*450;
					mainNodes[mainNodes.length] = element.id;
					element.className = "contentBoxLower boxLower";
					element.style.position = "absolute";
					element.style.left = "600px";
					element.style.top = "0px";
					var innerBox = document.createElement("div");
					innerBox.className = "innerBox";
					innerBox.innerHTML = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt";
					element.appendChild(innerBox);
					mainNode = element;
					dom.byId("container").appendChild(element);
				});

				// Set up a click handlers to run our animations
				on(swapButton, "click", function(evt){

					// chain the swap nodes animation
					// with another to fade out a background color in our container
					var anim = fx.chain([
						swapAnim(c1, c2)
						/*,
						baseFx.animateProperty({
							node: container,
							properties: {
								backgroundColor: "#fff"
							}
						})
						*/
					]);
					// before the animation begins, set initial container background
					//aspect.before(anim, "beforeBegin", function(){
						//domStyle.set(container, "backgroundColor", "#eee");
					//});

					// when the animation ends, toggle the originalOrder
					on(anim, "End", function(n1, n2){
						originalOrder = !originalOrder;
					});

					anim.play();
				});
			});
		</script>
</body>
</html>