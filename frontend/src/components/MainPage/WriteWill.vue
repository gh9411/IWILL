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

        <md-switch v-model="switch2"
          ><span v-if="switch2" style="color: black">이미지 추가하기</span
          ><span v-if="switch2 == false">이미지 추가하기</span></md-switch
        >
        <v-file-input
          v-if="switch2"
          @change="uploadFileImage"
          ref="willimage"
          small-chips
          label="Image input"
          accept="image/jpeg, image/png"
          enctype="multipart/form-data"
          style="margin-right:1em; margin-left:0.5em;"
        ></v-file-input>

        <md-switch v-model="switch3" style="margin-top: 1em"
          ><span v-if="switch3" style="color: black">영상 추가하기</span
          ><span v-if="switch3 == false">영상 추가하기</span></md-switch
        >
        <div class="text-center">
          <md-button class="md-danger" v-if="switch3" @click="checklegal"
            >영상의 법적효력을 알고싶다면!</md-button
          >
        </div>
        <modal v-if="this.modal.logalcheck" class="text-center">
          <template slot="header">
            <div class="py-3 mb-0">
              <h4 class="text-danger" style="font-weight:bold">
                영상이 법적효력을 가지는 방법!
              </h4>
            </div>
          </template>
          <template slot="body">
            <hr style="margin-bottom:30px;" />
            <h5
              class="my-5"
              style="margin-top:10px; font-weight:bold; text-align:left"
            >
              1. 1명 이상의 증인이 필요합니다.
            </h5>
            <h5 class="my-5" style="font-weight:bold; text-align:left">
              2. 성명과 날짜를 음성으로 남겨야 합니다. (본인, 증인 모두)
            </h5>
            <h5 class="my-5" style="font-weight:bold; text-align:left">
              3. 증인은 이익을 받는 가족과 미성년자는 불가합니다.
            </h5>
          </template>
          <template slot="footer">
            <div class="text-center">
              <md-button
                size="sm"
                class="md-simple md-default"
                @click="modal.logalcheck = false"
                >닫기</md-button
              >
            </div>
          </template>
        </modal>
        <v-file-input
          v-if="switch3"
          @change="uploadFileVideo"
          small-chips
          label="Video input"
          accept="video/mp4, video/avi"
          style="margin-right:1em; margin-left:0.5em;"
        ></v-file-input>

        <div style="margin-top: 1em; margin-right: 1em;">
          <md-switch v-model="switch1"
            ><span v-if="switch1" style="color: black">수신인 설정하기</span
            ><span v-if="switch1 == false">수신인 설정하기</span></md-switch
          >
        </div>

        <!-- 날짜 입력 & 유언장 수신인 -->
        <div class="container" v-if="switch1">
          <!-- 유언장 수신인 주소 -->
          <div class="who">
            <h3>누구에게?</h3>
            <md-chips
              small-chips
              v-model="receive"
              md-check-duplicated="true"
              md-placeholder="Add people email..."
              style="margin-top: 0px; padding-top: .2em;"
            ></md-chips>
          </div>
          <!-- 유언장 전달일자 -->
          <div>
            <h3>언제?</h3>
            <md-datepicker v-model="senddate" />
          </div>
        </div>

        <div class="box"></div>
        <!-- submit -->
        <div class="buttoncenter">
          <md-button class="md-button" @click="submit()"
            ><span style="font-size: 1rem;">글 남기기</span></md-button
          >
        </div>
      </v-form>
    </v-card>
  </div>
</template>

<script>
import { Modal } from "@/components";
export default {
  name: "WriteWill",
  components: { Modal },
  data() {
    let dateFormat = this.$material.locale.dateFormat || "yyyy-MM-dd";
    let now = new Date();
    const defaultForm = Object.freeze({
      title: "",
      content: ""
    });
    return {
      userdata: "",
      switch1: false,
      switch2: false,
      switch3: false,
      form: Object.assign({}, defaultForm),
      rules: {
        name: [val => (val || "").length > 0 || "제목은 필수사항입니다."]
      },
      content: "",
      willImage: null,
      willVideo: null,
      witness: null,
      receive: [],
      senddate: null,
      modal: {
        logalcheck: false
      }
    };
  },
  created() {
    this.userdata = this.$cookies.get("UserInfo");
  },
  methods: {
    checklegal() {
      this.modal.logalcheck = true;
    },
    uploadFileImage(e) {
      this.willImage = e;
    },
    uploadFileVideo(e) {
      this.willVideo = e;
    },
    submit() {
      let today = new Date();
      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1; // 월
      let date = today.getDate(); // 날짜
      let day = today.getDay(); // 요일
      let hours = today.getHours(); // 시
      let minutes = today.getMinutes(); // 분

      const createdDate =
        year + "." + month + "." + date + "-" + hours + "." + minutes;

      const formData = new FormData();
      formData.append("uid", this.userdata.email);
      formData.append("title", this.form.title);
      formData.append("content", this.form.content);
      formData.append("witness", this.witness);
      formData.append("receive", this.receive);
      formData.append("senddata", createdDate);
      console.log(formData);
      if (this.form.content == "") {
        return alert("내용을 적어주세요");
      }

      if (this.willVideo == null) {
        const file = new File(["test"], "nofile.mp4", { type: "text/mp4" });
        formData.append("video", file, file.name);
      } else {
        formData.append("video", this.willVideo, this.willVideo.name);
      }

      if (this.willImage == null) {
        const file = new File(["test"], "nofile.png", { type: "text/png" });
        formData.append("image", file, file.name);
      } else {
        formData.append("image", this.willImage, this.willImage.name);
      }

      this.$axios
        .post(this.$SERVER_URL + "will/register", formData, {
          headers: {
            "content-Type": "multipart/form-data"
          }
        })
        .then(res => {
          alert("글을 남겼습니다.");
          this.$router.go(0);
        })
        .catch(err => {
          console.error(err);
        });
    }
  },
  computed: {
    formIsValid() {
      return this.form.title && this.form.content;
    }
  }
};
</script>
