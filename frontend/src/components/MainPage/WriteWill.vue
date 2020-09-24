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
        <v-file-input show-size counter multiple label="File input" style="margin-right:1em; margin-left:0.5em;"></v-file-input>

        <!-- submit -->
        <div class="buttoncenter">
          <md-button class="md-button"><span style="font-size: 1rem;">글 남기기</span></md-button>
        </div>
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
