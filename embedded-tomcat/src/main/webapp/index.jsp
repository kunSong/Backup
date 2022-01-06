<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
		<div id="app">
			<div>
				<%@ page import="java.time.*" %>
				<% 
					out.println("Time is ");
					LocalDateTime ldt = LocalDateTime.now();
					out.println(ldt.toString());
				%>
			</div>
			<div>
				<ul>
					<!-- in => of ; item index -->
					<li v-for="(item, index) in messages">
						{{ item + '-' + index }}
					</li>
				</ul>
			</div>
			<div v-for="(value, name, index) of messagesSingleObject">
				{{ index + '. ' + name + '-' + value }}
			</div>
			<!-- 数组中对象的key，如果数组元素变化，不会调整顺序，原地更新 -->
			<!-- 支持对数组变化的响应，即使变更返回新数组也会重用DOM(智能启发式) -->
			<div v-for="item in messagesMultiObjects" :key="item.topic">
				{{ item.topic }}
			</div>
			<div v-for="num in even">
				{{ num }}
			</div>
			<div v-for="nums in numbersets">
				<div v-for="num in evenMethods(nums)">
					{{ num }}
				</div>
			</div>
			<div v-for="num in 10">
				{{ num }}
			</div>
			<div>
				<ul>
				   <!-- for multi dom block -->
				  <template v-for="item in messages">
					<li>{{ item }}</li>
				    <li class="divider" role="presentation"></li>
				  </template>
				</ul>
			</div>
			<div>
				<!-- v-for && v-if forbidden -->
				<!-- for priority high -->
				<ul v-for="item in messages" v-if="item === 'foo'">
					<li>{{ item }}</li>
				</ul>
				<!-- for skip -->
				<ul v-if="messages.length === 2">
					<li v-for="item in messages">{{ item }}</li>
				</ul>
				<p v-else>no message</p>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
		<script>
			var vm = new Vue({
				el: '#app',
				data: {
					messages: ['foo', 'bar'],
					messagesSingleObject: {
						topic: 'coupon',
						type: 'delay',
						tool: 'kafka'
					},
					messagesMultiObjects: [{topic: 'kafka'}, {topic: 'rocketmq'}],
					numbers: [1,2,3,4,5,6,7,8,9,10],
					numbersets: [[1,2,3,4,5],[6,7,8,9,10]]
				},
				methods: {
					evenMethods: function(sets) {
						return sets.filter(function(num) {
							return num % 2 == 0;
						})
					}
				},
				computed: {
					even: function() {
						return this.numbers.filter(function(num) {
							return num % 2 === 0;
						});
					}
				}
			});
		</script>
	</body>
</html>