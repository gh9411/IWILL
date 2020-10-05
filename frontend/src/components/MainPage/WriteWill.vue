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

          <md-switch v-model="switch2"><span v-if="switch2" style="color: black">이미지 추가하기</span><span v-if="switch2==false">이미지 추가하기</span></md-switch>
          <v-file-input 
            v-if="switch2" 
            @change="uploadFileImage"
            ref="willimage"
            small-chips label="Image input"
            accept="image/jpeg, image/png"
            enctype="multipart/form-data" 
            style="margin-right:1em; margin-left:0.5em;"
          ></v-file-input>

          <md-switch v-model="switch3" style="margin-top: 1em"><span v-if="switch3" style="color: black">영상 추가하기</span><span v-if="switch3==false">영상 추가하기</span></md-switch>
          <v-file-input v-if="switch3" @change="uploadFileVideo" small-chips  label="Video input" accept="video/mp4, video/avi" style="margin-right:1em; margin-left:0.5em;"></v-file-input>
      

        <div style="margin-top: 1em; margin-right: 1em;" >
          <md-switch v-model="switch1"><span v-if="switch1" style="color: black">수신인 설정하기</span><span v-if="switch1==false">수신인 설정하기</span></md-switch>
        </div>
        <!-- 날짜 입력 & 유언장 수신인 -->
        <div class="container" v-if="switch1">
          <!-- 유언장 수신인 주소 -->
          <div class="who">
            <h3>누구에게?</h3>
            <md-chips
              small-chips
              v-model="receive" 
              md-check-duplicated=true 
              md-placeholder="Add people email..." 
              style="margin-top: 0px; padding-top: .2em;"
            ></md-chips>

            
          </div>
          <!-- 유언장 전달일자 -->
          <div>
            <h3>언제?</h3>
            <md-datepicker v-model="senddate"/>
          </div>
        </div>

        <div class="box"></div>

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
      userdata: "",
      switch1: false,
      switch2: false,
      switch3: false,
      senddate: "",
      form: Object.assign({}, defaultForm),
      rules: {
        name: [val => (val || '').length > 0 || '제목은 필수사항입니다.'],
      },
      content: "",
      willImage: null,
      willVideo: null,
      witness: "",
      receive: [],
      
    };
  },
  created() {
    this.userdata = this.$cookies.get("UserInfo")
  },
  methods: {
    uploadFileImage: function (e) {
        this.willImage = e
    },
    uploadFileVideo(e) {
      this.willVideo = e
    },
    submit () {
      var fs = require("fs");
      let today = new Date();
      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1;  // 월
      let date = today.getDate();  // 날짜
      let day = today.getDay();  // 요일
      let hours = today.getHours(); // 시
      let minutes = today.getMinutes();  // 분

      const createdDate = year + '.' + month + '.' + date + "-" + hours + '.' + minutes

      const formData = new FormData();
      formData.append("uid", this.userdata.email);
      formData.append("title", this.form.title);
      formData.append("content", this.form.content);
      formData.append("witness", this.witness);
      formData.append("receive", this.receive);
      formData.append("senddata", createdDate);
      // multipartfile 구성
      formData.append("video", this.willVideo, this.willVideo.name);
      formData.append("image", this.willImage, this.willImage.name);
      

      this.$axios.post(this.$SERVER_URL + "will/register", formData, {headers: {
        "content-Type": "multipart/form-data"
        }
      })
        .then(
          res => {console.log(res)}
        )
        .catch(
          err => {console.log(err)}
        )         

      alert("글을 남겼습니다.")
    },
  },
  computed: {
    formIsValid () {
      return (
        this.form.title &&
        this.form.content
      )
    }
  }
};
</script>
