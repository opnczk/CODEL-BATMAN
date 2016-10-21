var KEY_ENTER=13;
$(document).ready(function(){
	var $input=$(".chat-input")
		,$sendButton=$(".chat-send")
		,$effectContainer=$(".chat-effect-container")

		,messages=0
		,bleeding=100
		,isFriendTyping=false
		,incomingMessages=0
		,lastMessage=""
	;
	var $rootPatentStr=".chat-window"
		,$inputStr=".chat-input"
		,$sendButtonStr=".chat-send"
		,$messagesContainerStr=".chat-messages"
		,$messagesListStr=".chat-messages-list"
		,$effectContainerStr=".chat-effect-container"
		,$infoContainerStr=".chat-info-container"

	;
	
	var lipsum="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	function gooOn(){
		setFilter('url(#goo)');
	}
	function gooOff(){
		setFilter('none');
	}
	function setFilter(value){
		$effectContainer.css({
			webkitFilter:value,
			mozFilter:value,
			filter:value,
		});
	}

	function addMessage(aimedInput, message,self){
		var rootParent = aimedInput.parents($rootPatentStr);
		var $messageContainer=$("<li/>")
			.addClass('chat-message '+(self?'chat-message-self':'chat-message-friend'))
			.appendTo(rootParent.find($messagesListStr))
		;
		var $messageBubble=$("<div/>")
			.addClass('chat-message-bubble')
			.appendTo($messageContainer)
		;
		$messageBubble.text(message);

		var oldScroll=rootParent.find($messagesContainerStr).scrollTop();
		rootParent.find($messagesContainerStr).scrollTop(9999999);
		var newScroll=rootParent.find($messagesContainerStr).scrollTop();
		var scrollDiff=newScroll-oldScroll;
		TweenMax.fromTo(
			rootParent.find($messagesListStr),0.4,{
				y:scrollDiff
			},{
				y:0,
				ease:Quint.easeOut
			}
		);

		return {
			$container:$messageContainer,
			$bubble:$messageBubble
		};
	}
	
	function sendMessage(aimedInput){
		var rootParent = aimedInput.parents($rootPatentStr);
		var message= aimedInput.text();
		
		if(message=="") return;
		
		lastMessage=message;

		var messageElements = addMessage(aimedInput,message,true)
			,$messageContainer=messageElements.$container
			,$messageBubble=messageElements.$bubble
		;

		var oldInputHeight= aimedInput.parents(".chat-input-bar").height();
		aimedInput.text('');
		updateChatHeight();
		var newInputHeight= aimedInput.parents(".chat-input-bar").height();
		var inputHeightDiff=newInputHeight-oldInputHeight

		var $messageEffect=$("<div/>")
			.addClass('chat-message-effect')
			.append($messageBubble.clone())
			.appendTo(rootParent.find($effectContainerStr))
			.css({
				left:aimedInput.position().left-12,
				top:aimedInput.position().top+bleeding+inputHeightDiff
			})
		;


		var messagePos=$messageBubble.offset();
		var effectPos=$messageEffect.offset();
		var pos={
			x:messagePos.left-effectPos.left,
			y:messagePos.top-effectPos.top
		}

		var $sendIcon=rootParent.find($sendButtonStr).children("i");
		TweenMax.to(
			$sendIcon,0.15,{
				x:30,
				y:-30,
				force3D:true,
				ease:Quad.easeOut,
				onComplete:function(){
					TweenMax.fromTo(
						$sendIcon,0.15,{
							x:-30,
							y:30
						},
						{
							x:0,
							y:0,
							force3D:true,
							ease:Quad.easeOut
						}
					);
				}
			}
		);

		gooOn();

		
		TweenMax.from(
			$messageBubble,0.8,{
				y:-pos.y,
				ease:Sine.easeInOut,
				force3D:true
			}
		);

		var startingScroll=rootParent.find($messagesContainerStr).scrollTop();
		var curScrollDiff=0;
		var effectYTransition;
		var setEffectYTransition=function(dest,dur,ease){
			return TweenMax.to(
				$messageEffect,dur,{
					y:dest,
					ease:ease,
					force3D:true,
					onUpdate:function(){
						var curScroll=rootParent.find($messagesContainerStr).scrollTop();
						var scrollDiff=curScroll-startingScroll;
						if(scrollDiff>0){
							curScrollDiff+=scrollDiff;
							startingScroll=curScroll;

							var time=effectYTransition.time();
							effectYTransition.kill();
							effectYTransition=setEffectYTransition(pos.y-curScrollDiff,0.8-time,Sine.easeOut);
						}
					}
				}
			);
		}

		effectYTransition=setEffectYTransition(pos.y,0.8,Sine.easeInOut);
		
		// effectYTransition.updateTo({y:800});

		TweenMax.from(
			$messageBubble,0.6,{
				delay:0.2,
				x:-pos.x,
				ease:Quad.easeInOut,
				force3D:true
			}
		);
		TweenMax.to(
			$messageEffect,0.6,{
				delay:0.2,
				x:pos.x,
				ease:Quad.easeInOut,
				force3D:true
			}
		);

		TweenMax.from(
			$messageBubble,0.2,{
				delay:0.65,
				opacity:0,
				ease:Quad.easeInOut,
				onComplete:function(){
					TweenMax.killTweensOf($messageEffect);
					$messageEffect.remove();
					if(!isFriendTyping)
						gooOff();
				}
			}
		);

		messages++;

		if(Math.random()<0.65 || lastMessage.indexOf("?")>-1 || messages==1) getReply(aimedInput);
	}
	function getReply(aimedInput){
		var rootParent = aimedInput.parents($rootPatentStr);
		
		if(incomingMessages>2) return;
		incomingMessages++;
		var typeStartDelay=1000+(lastMessage.length*40)+(Math.random()*1000);
		setTimeout(friendIsTyping(rootParent),typeStartDelay);

		var source=lipsum.toLowerCase();
		source=source.split(" ");
		var start=Math.round(Math.random()*(source.length-1));
		var length=Math.round(Math.random()*13)+1;
		var end=start+length;
		if(end>=source.length){
			end=source.length-1;
			length=end-start;
		}
		var message="";
		for (var i = 0; i < length; i++) {
			message+=source[start+i]+(i<length-1?" ":"");
		};
		message+=Math.random()<0.4?"?":"";
		message+=Math.random()<0.2?" :)":(Math.random()<0.2?" :(":"");

		var typeDelay=300+(message.length*50)+(Math.random()*1000);

		setTimeout(function(){
			receiveMessage(aimedInput,message);
		},typeDelay+typeStartDelay);

		setTimeout(function(){
			incomingMessages--;
			if(Math.random()<0.1){
				getReply(aimedInput);
			}
			if(incomingMessages<=0){
				friendStoppedTyping(rootParent);
			}
		},typeDelay+typeStartDelay);
	}
	function friendIsTyping(rootParent){
		if(isFriendTyping) return;

		isFriendTyping=true;

		var $dots=$("<div/>")
			.addClass('chat-effect-dots')
			.css({
				top:-30+bleeding,
				left:10
			})
			.appendTo(rootParent.find($effectContainerStr))
		;
		for (var i = 0; i < 3; i++) {
			var $dot=$("<div/>")
				.addClass("chat-effect-dot")
				.css({
					left:i*20
				})
				.appendTo($dots)
			;
			TweenMax.to($dot,0.3,{
				delay:-i*0.1,
				y:30,
				yoyo:true,
				repeat:-1,
				ease:Quad.easeInOut
			})
		};

		var $info=$("<div/>")
			.addClass("chat-info-typing")
			.text("Your friend is typing...")
			.css({
				transform:"translate3d(0,30px,0)"
			})
			.appendTo(rootParent.find($infoContainerStr))

		TweenMax.to($info, 0.3,{
			y:0,
			force3D:true
		});

		gooOn();
	}

	function friendStoppedTyping(rootParent){
		if(!isFriendTyping) return

		isFriendTyping=false;

		var $dots=rootParent.find($effectContainerStr).find(".chat-effect-dots");
		TweenMax.to($dots,0.3,{
			y:40,
			force3D:true,
			ease:Quad.easeIn,
		});

		var $info=rootParent.find($infoContainerStr).find(".chat-info-typing");
		TweenMax.to($info,0.3,{
			y:30,
			force3D:true,
			ease:Quad.easeIn,
			onComplete:function(){
				$dots.remove();
				$info.remove();

				gooOff();
			}
		});
	}
	function receiveMessage(aimedInput,message){
		
		var messageElements=addMessage(aimedInput,message,false)
			,$messageContainer=messageElements.$container
			,$messageBubble=messageElements.$bubble
		;

		TweenMax.set($messageBubble,{
			transformOrigin:"60px 50%"
		})
		TweenMax.from($messageBubble,0.4,{
			scale:0,
			force3D:true,
			ease:Back.easeOut
		})
		TweenMax.from($messageBubble,0.4,{
			x:-100,
			force3D:true,
			ease:Quint.easeOut
		})
	}

	function updateChatHeight(aimedInput){
		if(!aimedInput) aimedInput = $input;
		aimedInput.parents($rootPatentStr).find($messagesContainerStr).css({
			height: aimedInput.parents($rootPatentStr).css('height')-aimedInput.parents(".chat-input-bar").height()
		});
	}

	function openChatTab(){
		var template = $('#chat-window-template');
		var chatSystem = $('#chat-system');
		var nbOpenTabs = chatSystem.children('.chat-window').length;
		
		if(nbOpenTabs >= 6){
			console.log('No more you moron !');
			return;
		}
		var wot = template.after(template.html());
		var newTab = template.next();
		
		if(nbOpenTabs != 0){
			console.log((320*nbOpenTabs-((nbOpenTabs-1)*50))+'px');
			newTab.css('right', (320*nbOpenTabs-((nbOpenTabs-1)*50))+'px');
		}
	
		bindEvents(newTab.find(".chat-input"), newTab.find(".chat-send"));
	}
	
	function rearrangeTabs(){
		var chatSystem = $('#chat-system');
		var tabs = chatSystem.children('.chat-window');
		var nbOpenTabs = tabs.length;
		
		tabs.each(function( index ) {
		  var amount = 0;
		  if(index == 0)
				amount = 30;
			amount = (320*index-((index-1)*50));
			$( this ).animate({right: amount,}, 500);
		});
	}
	
	$('.chat-contact').click(function(event){
		openChatTab();
	});
	
	function bindEvents(input, sendButton){
		input.keydown(function(event) {
		if(event.keyCode==KEY_ENTER){
			event.preventDefault();
			sendMessage($(event.target));
		}
		});
		sendButton.click(function(event){
			event.preventDefault();
			sendMessage($(event.target));
			// $input.focus();
		});
		sendButton.on("touchstart",function(event){
			event.preventDefault();
			sendMessage($(event.target));
			// $input.focus();
		});

		input.on("input",function(e){
			var aimedInput = $(e.target);
			updateChatHeight(aimedInput);
		});
		
			var bottomValue = null;
			//$(".chat-window .chat-messages").slimScroll();
			/*load_data = {'fetch':1};
			window.setInterval(function(){
			 $.post('chat-shout-box', load_data,  function(data) {
				$('.message_box').html(data);
			 });
			 console.log('ajax Call');
			}, 5000);
				$("#shout_message").keypress(function(evt) {
				if(evt.which == 13) {
						var iusername = $('#shout_username').val();
						var imessage = $('#shout_message').val();
						post_data = {'username':iusername, 'message':imessage};
						$.post('chat-shout-box', post_data, function(data) {
							$(data).hide().appendTo('.message_box').fadeIn();
							var scrolltoh = $('.message_box')[0].scrollHeight;
							$('.message_box').scrollTop(scrolltoh);
							$('#shout_message').val('');
						}).fail(function(err) { 
						alert(err.statusText); 
						});
					}
			});*/
			$(".chat-close-button").click(function (e) {
				var elem = $(e.target).parents('.chat-window');
				elem.remove();
				rearrangeTabs();
			});
			$(".chat-action-button").click(function (e) {
				var elem = $(e.target).parents('.chat-window');
				var button = $(e.target).parents('.chat-action-button');
				//var chatWindow = ;
				var toggleState = elem.css('bottom');
				if(bottomValue == null)
					bottomValue = toggleState;
				if(toggleState == '0px')
				{
					elem.animate({bottom: bottomValue}, 500,function(){
						var btn = button.find('.close_btn');
						btn.attr('class', 'open_btn');
						btn.find('i').attr('class', 'fa fa-chevron-up');
					});
				}else{
					elem.animate({bottom: "0px"}, 500,function(){
						var btn = button.find('.open_btn');
						btn.attr('class', 'close_btn');
						btn.find('i').attr('class', 'fa fa-minus');
					});
				}
			});
	}
	
	bindEvents($input, $sendButton);

	gooOff();
	updateChatHeight();
})