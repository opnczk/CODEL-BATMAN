/**
 * demo.js
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2016, Codrops
 * http://www.codrops.com
 */
;(function(window) {

	'use strict';

	// taken from mo.js demos
	function isIOSSafari() {
		var userAgent;
		userAgent = window.navigator.userAgent;
		return userAgent.match(/iPad/i) || userAgent.match(/iPhone/i);
	};

	// taken from mo.js demos
	function isTouch() {
		var isIETouch;
		isIETouch = navigator.maxTouchPoints > 0 || navigator.msMaxTouchPoints > 0;
		return [].indexOf.call(window, 'ontouchstart') >= 0 || isIETouch;
	};
	
	// taken from mo.js demos
	var isIOS = isIOSSafari(),
		clickHandler = isIOS || isTouch() ? 'touchstart' : 'click';

	function extend( a, b ) {
		for( var key in b ) { 
			if( b.hasOwnProperty( key ) ) {
				a[key] = b[key];
			}
		}
		return a;
	}

	function Animocon(el, options) {
		this.el = el;
		this.options = extend( {}, this.options );
		extend( this.options, options );

		this.checked = false;

		this.timeline = new mojs.Timeline();
		
		for(var i = 0, len = this.options.tweens.length; i < len; ++i) {
			this.timeline.add(this.options.tweens[i]);
		}

		var self = this;
		this.el.addEventListener(clickHandler, function() {
			if( self.checked ) {
				self.options.onUnCheck();
			}
			else {
				self.options.onCheck();
				self.timeline.start();
			}
			self.checked = !self.checked;
		});
	}

	Animocon.prototype.options = {
		tweens : [
			new mojs.Burst({
				shape : 'circle',
				isRunLess: true
			})
		],
		onCheck : function() { return false; },
		onUnCheck : function() { return false; }
	};

	// grid items:
	//var items = [].slice.call(document.querySelectorAll('ol.grid > .grid__item'));

	function init() {
		/* Icon 14 */
		var el14 = document.querySelectorAll('.heart-btn')[0].querySelector('button.icobutton'), el14span = el14.querySelector('span'), el14counter = el14.querySelector('span.icobutton__text');
		new Animocon(el14, {
			tweens : [
				// ring animation
				new mojs.Transit({
					parent: el14,
					duration: 750,
					type: 'circle',
					radius: {0: 40},
					fill: 'transparent',
					stroke: '#3c8dbc',/*#F35186*/
					strokeWidth: {35:0},
					opacity: 0.2,
					x: '50%',     
					y: '45%',
					isRunLess: true,
					easing: mojs.easing.bezier(0, 1, 0.5, 1)
				}),
				new mojs.Transit({
					parent: el14,
					duration: 500,
					delay: 100,
					type: 'circle',
					radius: {0: 20},
					fill: 'transparent',
					stroke: '#3c8dbc',
					strokeWidth: {5:0},
					opacity: 0.2,
					x: '50%', 
					y: '50%',
					shiftX : 40, 
					shiftY : -60,
					isRunLess: true,
					easing: mojs.easing.sin.out
				}),
				new mojs.Transit({
					parent: el14,
					duration: 500,
					delay: 180,
					type: 'circle',
					radius: {0: 10},
					fill: 'transparent',
					stroke: '#3c8dbc',
					strokeWidth: {5:0},
					opacity: 0.5,
					x: '50%', 
					y: '50%',
					shiftX : -10, 
					shiftY : -80,
					isRunLess: true,
					easing: mojs.easing.sin.out
				}),
				new mojs.Transit({
					parent: el14,
					duration: 800,
					delay: 240,
					type: 'circle',
					radius: {0: 20},
					fill: 'transparent',
					stroke: '#3c8dbc',
					strokeWidth: {5:0},
					opacity: 0.3,
					x: '50%', 
					y: '50%',
					shiftX : -70, 
					shiftY : -10,
					isRunLess: true,
					easing: mojs.easing.sin.out
				}),
				new mojs.Transit({
					parent: el14,
					duration: 800,
					delay: 240,
					type: 'circle',
					radius: {0: 20},
					fill: 'transparent',
					stroke: '#3c8dbc',
					strokeWidth: {5:0},
					opacity: 0.4,
					x: '50%', 
					y: '50%',
					shiftX : 80, 
					shiftY : -50,
					isRunLess: true,
					easing: mojs.easing.sin.out
				}),
				new mojs.Transit({
					parent: el14,
					duration: 1000,
					delay: 300,
					type: 'circle',
					radius: {0: 15},
					fill: 'transparent',
					stroke: '#3c8dbc',
					strokeWidth: {5:0},
					opacity: 0.2,
					x: '50%', 
					y: '50%',
					shiftX : 20, 
					shiftY : -100,
					isRunLess: true,
					easing: mojs.easing.sin.out
				}),
				new mojs.Transit({
					parent: el14,
					duration: 600,
					delay: 330,
					type: 'circle',
					radius: {0: 25},
					fill: 'transparent',
					stroke: '#3c8dbc',
					strokeWidth: {5:0},
					opacity: 0.4,
					x: '50%', 
					y: '50%',
					shiftX : -40, 
					shiftY : -90,
					isRunLess: true,
					easing: mojs.easing.sin.out
				}),
				// icon scale animation
				new mojs.Tween({
					duration : 1200,
					easing: mojs.easing.ease.out,
					onUpdate: function(progress) {
						if(progress > 0.3) {
							var elasticOutProgress = mojs.easing.elastic.out(1.43*progress-0.43);
							el14span.style.WebkitTransform = el14span.style.transform = 'scale3d(' + elasticOutProgress + ',' + elasticOutProgress + ',1)';
						}
						else {
							el14span.style.WebkitTransform = el14span.style.transform = 'scale3d(0,0,1)';
						}
					}
				})
			],
			onCheck : function() {
				el14.style.color = '#3c8dbc';
				if(el14counter)
				el14counter.innerHTML = Number(el14counter.innerHTML) + 1;
			},
			onUnCheck : function() {
				el14.style.color = '#7A7B7D';
				if(el14counter){
					var current = Number(el14counter.innerHTML);
					el14counter.innerHTML = current > 1 ? Number(el14counter.innerHTML) - 1 : '';
				}
			}
		});
		/* Icon 14 */

		/* Icon 17 */
		/*var el17 = document.querySelectorAll('.unicorn-btn')[0].querySelector('button.icobutton'), el17SVG = el17.querySelector('svg');
		var translationCurve17 = mojs.easing.path('M0,100 C0,72 10,-0.1 50,0 C89.6,0.1 100,72 100,100');
		new Animocon(el17, {
			tweens : [
				// burst animation (line1)
				new mojs.Burst({
					parent: el17,
					duration: 600,
					shape : 'circle',
					x: '65%',
					y: '40%',
					childOptions: { 
						radius: {20:0},
						type: 'line',
						stroke: '#bf62a6',
						strokeWidth: 2
					},
					radius: {40:120},
					angle: 70,
					count: 1,
					isRunLess: true,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
				}),
				// burst animation (line2)
				new mojs.Burst({
					parent: el17,
					duration: 600,
					shape : 'circle',
					x: '65%',
					y: '40%',
					childOptions: { 
						radius: {20:0},
						type: 'line',
						stroke: '#f28c33',
						strokeWidth: 2
					},
					radius: {40:120},
					angle: 74,
					count: 1,
					isRunLess: true,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
				}),
				// burst animation (line3)
				new mojs.Burst({
					parent: el17,
					duration: 600,
					shape : 'circle',
					x: '65%',
					y: '40%',
					childOptions: { 
						radius: {20:0},
						type: 'line',
						stroke: '#f5d63d',
						strokeWidth: 2
					},
					radius: {40:120},
					angle: 78,
					count: 1,
					isRunLess: true,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
				}),
				// burst animation (line4)
				new mojs.Burst({
					parent: el17,
					duration: 600,
					shape : 'circle',
					x: '65%',
					y: '40%',
					childOptions: { 
						radius: {20:0},
						type: 'line',
						stroke: '#79c267',
						strokeWidth: 2
					},
					radius: {40:120},
					angle: 82,
					count: 1,
					isRunLess: true,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
				}),
				// burst animation (line5)
				new mojs.Burst({
					parent: el17,
					duration: 600,
					shape : 'circle',
					x: '65%',
					y: '40%',
					childOptions: { 
						radius: {20:0},
						type: 'line',
						stroke: '#78c5d6',
						strokeWidth: 2
					},
					radius: {40:120},
					angle: 86,
					count: 1,
					isRunLess: true,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
				}),
				// burst animation (circles)
				new mojs.Burst({
					parent: el17,
					duration: 1600,
					shape : 'circle',
					x: '65%',
					y: '40%',
					fill: ['#bf62a6','#f28c33','#f5d63d','#79c267','#78c5d6'],
					childOptions: { 
						radius: {'rand(20,5)':0},
						delay: [0,350,200,150,400]
					},
					radius: {20:50},
					degree: 20,
					angle: 70,
					isSwirl: true,
					swirlSize: 4,
					count: 4,
					opacity: 0.6,
					isRunLess: true,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
				}),
				// icon scale animation
				new mojs.Tween({
					duration : 800,
					easing: mojs.easing.bezier(0.1, 1, 0.3, 1),
					onUpdate: function(progress) {
						var translationProgress = translationCurve17(progress);
							el17SVG.style.WebkitTransform = el17SVG.style.transform = 'translate3d(' + -20 * translationProgress + '%,0,0)';	
					}
				})
			],
			onCheck : function() {
				el17SVG.style.fill = '#F198CA';
			},
			onUnCheck : function() {
				el17SVG.style.fill = '#C0C1C3';
			}
		});*/
		/* Icon 17 */
		
		// bursts when hovering the mo.js link
		/*var molinkEl = document.querySelector('.special-link'),
			moTimeline = new mojs.Timeline(),
			moburst1 = new mojs.Burst({
				parent: molinkEl,
				duration: 1300,
				shape : 'circle',
				fill : [ '#988ADE', '#DE8AA0', '#8AAEDE', '#8ADEAD', '#DEC58A', '#8AD1DE' ],
				x: '0%',
				y: '-50%',
				radius: {0:60},
				count: 6,
				isRunLess: true,
				easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
			}),
			moburst2 = new mojs.Burst({
				parent: molinkEl,
				duration: 1600,
				delay: 100,
				shape : 'circle',
				fill : [ '#988ADE', '#DE8AA0', '#8AAEDE', '#8ADEAD', '#DEC58A', '#8AD1DE' ],
				x: '-100%',
				y: '-20%',
				radius: {0:120},
				count: 14,
				isRunLess: true,
				easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
			}),
			moburst3 = new mojs.Burst({
				parent: molinkEl,
				duration: 1500,
				delay: 200,
				shape : 'circle',
				fill : [ '#988ADE', '#DE8AA0', '#8AAEDE', '#8ADEAD', '#DEC58A', '#8AD1DE' ],
				x: '130%',
				y: '-70%',
				radius: {0:90},
				count: 8,
				isRunLess: true,
				easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
			}),
			moburst4 = new mojs.Burst({
				parent: molinkEl,
				duration: 2000,
				delay: 300,
				shape : 'circle',
				fill : [ '#988ADE', '#DE8AA0', '#8AAEDE', '#8ADEAD', '#DEC58A', '#8AD1DE' ],
				x: '-20%',
				y: '-150%',
				radius: {0:60},
				count: 14,
				isRunLess: true,
				easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
			}),
			moburst5 = new mojs.Burst({
				parent: molinkEl,
				duration: 1400,
				delay: 400,
				shape : 'circle',
				fill : [ '#988ADE', '#DE8AA0', '#8AAEDE', '#8ADEAD', '#DEC58A', '#8AD1DE' ],
				x: '30%',
				y: '-100%',
				radius: {0:60},
				count: 12,
				isRunLess: true,
				easing: mojs.easing.bezier(0.1, 1, 0.3, 1)
			});

		moTimeline.add(moburst1, moburst2, moburst3, moburst4, moburst5);
		molinkEl.addEventListener('mouseenter', function() {
			moTimeline.start();
		});*/
	}
	
	init();

})(window);