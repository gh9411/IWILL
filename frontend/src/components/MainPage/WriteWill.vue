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
        <!-- v-model을 files라는 data에 연결했지만, 메서드를 보면, 그냥 e에 다 담겨 있습니다 
        이걸 백으로 어떻게 넘기는게 편할지 몰라 일단 그냥 두겠습니다. -->
        <v-file-input @change="uploadFile" chips multiple label="File input" style="margin-right:1em; margin-left:0.5em;"></v-file-input>


        <!-- 날짜 입력 & 유언장 수신인 -->
        <div class="container">
          <!-- 유언장 수신인 주소 -->
          <div class="who">
            <h3>누구에게?</h3>
            <md-chips
              v-model="people" 
              md-check-duplicated=true 
              md-placeholder="Add people..." 
              style="margin-top: 0px; padding-top: .2em;"
            ></md-chips>
            
          </div>
          <!-- 유언장 전달일자 -->
          <div>
            <h3>언제?</h3>
            <md-datepicker v-model="senddate"/>
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
  name: "WriteWill",
  components: {
  },
  data() {
    let dateFormat = this.$material.locale.dateFormat || 'yyyy-MM-dd'
    let now = new Date()
    const defaultForm = Object.freeze({
      title: '',
      content: '',
    })
    return {
      senddate: "",
      form: Object.assign({}, defaultForm),
      rules: {
        name: [val => (val || '').length > 0 || '제목은 필수사항입니다.'],
      },
      Topic: "",
      content: "",
      files: [],
      people: [],
    };
  },
  methods: {
    uploadFile(e) {
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
