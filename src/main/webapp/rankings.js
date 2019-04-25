/**
 * 
 */

Vue.config.devtools = true


Vue.component('rankings', {
	data: function () {
		 return {	
			 scores : []		 }
	  },
		methods: {
			onRankings: function (event) {
				axios
					.get('https://innovationday-237209.appspot.com/rankings')
					.then(response => (this.scores = response.data))
			 }
	  },
	template: `
	<div>
		<button type="button" class="btn btn-info" v-on:click="onRankings">View rankings</button>
		<table class="table table-sm">
			<tbody>
				<tr>
			    	<th>Numéro</th>
			    	<th>Equipe</th>
				</tr>
				<tr>
			    	<td>1</td>
			    	<td>HomeVR</td>
				</tr>
				<tr>
			    	<td>2</td>
			    	<td>Speak’up</td>
				</tr>
				<tr>
			    	<td>3</td>
			    	<td>Leaf’tube</td>
				</tr>
				<tr>
			    	<td>4</td>
			    	<td>Hi-light</td>
				</tr>
				<tr>
			    	<td>5</td>
			    	<td>Comfortech</td>
				</tr>
				<tr>
			    	<td>6</td>
			    	<td>ExoVR</td>
				</tr>
				<tr>
			    	<td>7</td>
			    	<td>SAEC</td>
				</tr>
				<tr>
			    	<td>8</td>
			    	<td>Wordhunt</td>
				</tr>
				<tr>
			    	<td>9</td>
			    	<td>SALVE</td>
				</tr>
				<tr>
			    	<td>10</td>
			    	<td>Grappe</td>
				</tr>
			</tbody>
		</table>
		Le classement est donné en ordre inverse: meilleure note 1, plus mauvaise note 5.
		<table class="table table-sm">
		<tbody>
			<tr>
			    <th>Numéro équipe</th>
			    <th>Note</th>
			</tr>
			<tr v-for="score in scores">
			<td><b>{{score.teamNumber}}</b></td>
			<td>{{score.mark}}</td>
			</tr>
		</tbody> 
		</table>
	 </div>
		`
})

	
new Vue({ el: '#components-rankings' })
