<template>
  <div class="writeWILL">
    <h3>글 남기기</h3>

    <v-card flat>
      <v-form ref="form" @submit.prevent="submit">
        <v-container fluid>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="form.title"
                :rules="rules.name"
                label="제목"
                required
              ></v-text-field>
            </v-col>
            
            <v-col cols="12">
              <v-textarea v-model="form.content">
                <template v-slot:label>
                  <div>내용</div>
                </template>
              </v-textarea>
            </v-col>
          </v-row>
        </v-container>
        <v-file-input show-size counter multiple label="File input"></v-file-input>

        <md-button class="md-button"><span style="font-size: 1rem">글 남기기</span></md-button>
      </v-form>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "writeWILL",
  data() {
    const defaultForm = Object.freeze({
      title: '',
      content: '',
    })
    return {
      form: Object.assign({}, defaultForm),
      rules: {
        name: [val => (val || '').length > 0 || '제목은 필수사항입니다.'],
      },
      Topic: "",
      content: "",
      
    };
  },
  methods: {
    uploadFile(e) {
      let file = e
      console.log(e)
    },
    
    submit () {
      //만들어야함.
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
