<template>
  <div class="writeWILL">

    <v-card class="willcard" flat>
      <v-form ref="form" @submit.prevent="submit">
        <v-container fluid>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="form.title"
                :rules="rules.name"
                label="Title"
                required
              ></v-text-field>
            </v-col>
            
            <v-col cols="12">
              <v-textarea v-model="form.content" auto-grow row-height="50">
                <template v-slot:label>
                  <div>content</div>
                </template>
              </v-textarea>
            </v-col>
          </v-row>
        </v-container>
        <!-- 파일 업로드 -->
        <!-- <v-file-input 
          show-size 
          counter 
          multiple 
          chips="True" 
          placeholder="한번에 여러 파일을 선택해 업로드도 가능합니다." 
          label="File input"  
          style="margin-right:1em; margin-left:0.5em;"
        ></v-file-input> -->
        <v-file-input
          v-model="files"
          placeholder="여러 파일 업로드도 가능합니다."
          label="File input"
          multiple
          prepend-icon="mdi-paperclip"
          style="margin-right:1em; margin-left:0.5em;"
        >
          <template v-slot:selection="{ text }">
            <v-chip small label color="black">{{ text }}</v-chip>
          </template>
        </v-file-input>
        <p>{{files}}</p>


        <!-- 날짜 입력 & 유언장 수신인 -->
        <div class="container">
          <!-- 유언장 수신인 주소 -->
          <div>
            <h3>누구에게?</h3>
            <md-chips v-model="people" md-placeholder="Add people..."></md-chips>
          </div>
          
          <div class="block">
            <h3>언제?</h3>
            <md-datepicker v-model="senddate" aria-placeholder="dd"/>
          </div>
        </div>
        <!-- submit -->
        <div class="buttoncenter">
          <md-button class="md-button" @click="submit()"><span style="font-size: 1rem;">글 남기기</span></md-button>
        </div>
      </v-form>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "writeWILL",
  data() {
    let dateFormat = this.$material.locale.dateFormat || 'yyyy-MM-dd'
    let now = new Date()
    const defaultForm = Object.freeze({
      title: '',
      content: '',
    })
    return {
      senddate: "",
      people: [],
      form: Object.assign({}, defaultForm),
      rules: {
        name: [val => (val || '').length > 0 || '제목은 필수사항입니다.'],
      },
      Topic: "",
      content: "",
      files: [],      
    };
  },
  methods: {
    uploadFile(e) {
      let file = e
      console.log(e)
    },
    
    submit () {
      //만들어야함.
      alert("글을 남겼습니다.")
    },
  },
  computed: {
    formIsValid () {
      return (
        this.form.title &&
        this.form.content
      )
    },
  },
};
</script>

<style></style>
