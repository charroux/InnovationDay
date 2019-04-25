/**
 * 
 */

Vue.config.devtools = true


Vue.component('assessment', {
	data: function () {
		 return {	
			 teamNumber : 1, 
			 assessmentTable : [],
			 assess : [],
			 saveValidation : false,
			 teamReady : 0,
			 erreur : false,
			 erreurMessage : ""
		 }
	  },
		methods: {
			onTeamNumber: function (event) {
				this.teamReady = 1;
				axios
					.get('https://innovationday-237209.appspot.com/assessmentTable')
					.then(response => {
						this.assessmentTable = response.data
						this.saveValidation = false
						this.teamReady = 2
					})
					.catch(error => {						
	  					this.erreur = true;
	  					this.erreurMessage = error.response.statusText
					});
			 },
	  		onSaveAssessment: function (event) {
	  			axios.put('https://innovationday-237209.appspot.com/teamAssessment/' + this.teamNumber , this.assess)
	  			.then(response => {
	  				this.saveValidation = true
		  			this.teamReady = 0
		  		})
	  			.catch(error => {
	  				this.erreur = true;
	  				this.erreurMessage = error.response.statusText
	  			});	  			
	  		},
	  		onCancelAssessment: function (event) {
		  		this.saveValidation = false
		  		this.teamReady = 0
		  	},
		  	onErrorClosed: function (event) {
		  		this.erreur = false
		  	}			 
		},
	template: 		`
	<div>
	
		<div>
			<button v-if="erreur" type="button" class="btn btn-danger" v-on:click="onErrorClosed">Erreur : {{erreurMessage}} (cliquez pour fermer)</button>
		</div>	
	
		<form>
		<div class="row">
			<div class="col">Choisir une équipe : </div>
			<!-- <div class="col"><input v-model="teamNumber" size="4" pattern="[0-9]+"></div> -->
			<div class="col">
				<select v-model="teamNumber">
			  		<option disabled value="">Choisissez</option>
			  		<option value="1">HomeVR</option>
			  		<option value="2">"Speak’up</option>
			  		<option value="3">Leaf’tube</option>
			  		<option value="4">Hi-light</option>
			  		<option value="5">Comfortech</option>
			  		<option value="6">ExoVR</option>
			  		<option value="7">SAEC</option>
			  		<option value="8">Wordhunt</option>
			  		<option value="9">SALVE</option>
			  		<option value="10">Grappe</option>			  		
				</select> 
			</div>
			<div class="col">
				<button v-if="teamReady == 0" type="button" class="btn btn-info" v-on:click="onTeamNumber">Sélectionner l'équipe {{teamNumber}}</button>
				<label v-if="teamReady == 1">Chargement...</label>
				<label v-if="teamReady == 2">Equipe {{teamNumber}} prête à la saisie</label>
			</div>
		</div>	
		</form>

		<table class="table table-sm">
		<tbody>
						
			<tr class="table-active" v-for="(assessment, index) in assessmentTable">
			<td><b>{{assessment.criterion}}</b></td>
	       	<td v-for="values in assessment.criterionValues">
	       		<input type="radio" :value="values" v-model="assess[index]">
	       		{{values.name}}
	       	</td>
			</tr>
			
		</tbody> 
		</table>
		
		<div class="row">
			<div class="col"><button type="button" class="btn btn-info" v-if="teamReady" v-on:click="onSaveAssessment">Enregistrer</button></div>
			<div class="col"><button type="button" class="btn btn-info" v-if="teamReady" v-on:click="onCancelAssessment">Annuler</button></div>
			<div class="col"><label v-if="saveValidation">Evaluation enregistrée !</label></div>
		</div>

	 </div>
		`
})

	
new Vue({ el: '#components-assessment' })
