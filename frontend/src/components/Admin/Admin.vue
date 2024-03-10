<script setup>
import {ref, reactive, onMounted, computed, getCurrentInstance} from 'vue';
import {useRoute} from 'vue-router';
import SideBar from "@/components/Admin/SideBar.vue";
import axiosInstance from "@/utils/axios";
import * as adminUtils from "@/components/Admin/admin";
import {formatTime} from "@/components/Student/pages/Message/utils";
import edit from "@element-plus/icons/lib/Edit";

const instance = getCurrentInstance();
const loading = ref(false);
const setting = ref('studentAcc');
const editDialogVisible = ref(false);
const editedItem = ref({});
const searchFilter = ref("");
const studentAccColumns = [
  { prop: 'id', label: '学号', sortable: true, searchable: true },
  { prop: 'name', label: '姓名', sortable: false, searchable: true },
  { prop: 'type', label: '类别', sortable: false, searchable: false,
    filter: [ 
              {text: '博士', value: 'Doctoral'},
              {text: '硕士', value: 'Master'}
            ]
  },
  { prop: 'sex', label: '性别', sortable: false, searchable: false,
    filter: [
      {text: '男', value: 'Male'},
      {text: '女', value: 'Female'}
    ]
  },

];
const studentTeamColumns = [
  { prop: 'id', label: '队伍编号', sortable: true, searchable: true },
  { prop: 'name', label: '队伍名字'},
  { }
];
const dormSelectColumns = [
  { prop: 'roomNumber', label: '房号' },
  { prop: 'roomCapacity', label: '房间容量' },
  { prop: 'roomType', label: '房间类型' },
  { prop: 'building', subProp: 'buildingName', label: '楼栋' },
  { prop: 'building', subProp: 'zoningName', label: '分区' },
]
const selectTimeColumns = [
  { prop: 'id', label: 'ID'},
  { prop: 'preStartTime', label: '组队开始时间'},
  { prop: 'preEndTime', label: '组队结束时间'},
  { prop: 'selectStartTime', label: '选宿舍开始时间'},
  { prop: 'selectEndTime', label: '选宿舍结束时间'},
  { prop: 'sex', label: '性别'},
  { prop: 'type', label: '学生类型'},
]
const zoningColumns = [
  { prop: 'id', label: 'ID'},
  { prop: 'zoningName', label: '分区名字'},
]
const buildingColumns = [
  { prop: 'id', label: 'ID'},
  { prop: 'buildingName', label: '楼栋名字'},
  { prop: 'zoningName', label: '分区名字'},
  { prop: 'buildingDescription', label: '楼栋描述'},
]
const studentAccData = ref([])
const studentTeamData = ref([])
const dormData = ref([])
const noticeData = ref([])
const selectTimeData = ref([])
const buildingData = ref([])
const zoningData = ref([])
const sendNoticeData = ref({
  title: '',
  content: ''
})

const newZoningData = ref({
  id: null,
  zoningName: ''
})

const data = reactive({
  studentAcc: studentAccData,
  studentTeam: studentTeamData,
  dormSelect: dormData,
  selectTime: selectTimeData,
  building: buildingData,
  zoning: zoningData,
});

const columns = reactive({
  studentAcc: studentAccColumns,
  studentTeam: studentTeamColumns,
  dormSelect: dormSelectColumns,
  selectTime: selectTimeColumns,
  building: buildingColumns,
  zoning: zoningColumns,
});

const route = useRoute();

onMounted(() => {
  setting.value = route.query.setting;
  fetchData();
});

async function fetchData() {
  loading.value = true
  switch (setting.value) {
    case 'studentAcc':
      studentAccData.value = await adminUtils.fetchStudentAccData();
      console.log("studentAccData", studentAccData.value)
      break;
    case 'studentTeam':
      studentTeamData.value = await adminUtils.fetchStudentTeamData();
      console.log("studentTeamData", studentTeamData.value)
      break;
    case 'dormSelect':
      dormData.value = await adminUtils.fetchDormData();
      console.log("dormData", dormData.value)
      break;
    case 'selectTime':
      selectTimeData.value = await adminUtils.fetchSelectTimeData();
      console.log("selectTimeData", selectTimeData.value)
      break;
    case 'building':
      buildingData.value = await adminUtils.fetchBuildingData();
      console.log("buildingData", buildingData.value)
      break;
    case 'zoning':
      zoningData.value = await adminUtils.fetchZoningData();
      console.log("zoningData", zoningData.value)
      break;
    default:
      break;
  }
  loading.value = false
};

const selectedTeamMembers = ref([]);
const selectedDormMembers = ref([]);
async function handleExpandChange(row, expanded) {
  if (expanded) {
    if(setting.value === 'dormSelect') {
      selectedDormMembers.value = await adminUtils.fetchDormMembers(row.id);
      console.log("selectedDorm", selectedDormMembers.value)
    }
    else if (setting.value === 'studentTeam') {
      selectedTeamMembers.value = await adminUtils.fetchTeamMembers(row.id);
      console.log("selectedTeamMembers", selectedTeamMembers.value)
    }
  }
}

async function onSendNotice() {
  let json_content = "{\"title\": \"" + sendNoticeData.value.title + "\", \"content\": \"" + sendNoticeData.value.content + "\"}"
  await axiosInstance.post('/teacher/notices', {
    content: json_content
  }).then(response => {
    console.log(response);
    if (response.status === 200) {
      if (response.data.code === 0) {
        alert("发送成功")
      }
      alert("发送失败")
    }
    else {
    }
  }).catch(error => {
    console.error(error);
  });
}

async function saveNewZoning(data) {
  await axiosInstance.post('/teacher/zoning', data)
      .then(response => {
        console.log(response)
        if (response.status === 200 && response.data.code === 0) {
          alert("保存成功")
        }
        else {
          alert("保存失败")
        }
      }).catch(error => {
        console.error(error);
      });
}
async function onSearch() {
  await fetchData();
}

async function onImport(params) {
  switch (setting.value) {
    case 'studentAcc':
      await adminUtils.importStudentAccounts(params.file);
      break;
    case 'studentTeam':
      // Import student team data
      break;
    case 'dormSelect':
      await adminUtils.importDormSelect(params.file)
      break;
    default:
      break;
  }
  await fetchData();
}

async function onExport() {
  await adminUtils.exportDormSelect()
}

const editItem = (item) => {
  console.log("editItem", item)
  editedItem.value = {...item};
  editDialogVisible.value = true;
};

const deleteItem = async (item) => {
  switch (setting.value) {
    case 'studentAcc':
      await adminUtils.deleteStudentAcc(editedItem.value)
      break
    case 'dormSelect':
      await adminUtils.deleteDormSelect(editedItem.value)
      break
    case 'selectTime':
      await adminUtils.deleteSelectTime(editedItem.value)
      break
    case 'building':
      await adminUtils.deleteBuilding(editedItem.value)
      break
    case 'zoning':
      await adminUtils.deleteZoning(editedItem.value)
      break
  }
  confirmDialogVisible.value = false;
  await fetchData()
};

const confirmDialogVisible = ref(false);
const newZoningDialogVisible = ref(false);
const newBuildingDialogVisible = ref(false);
const onDelete = (item) => {
  editedItem.value = {...item};
  confirmDialogVisible.value = true;
};

const saveItem = async () => {
  editDialogVisible.value = false;
  switch (setting.value) {
    case 'studentAcc':
      await adminUtils.updateStudentAcc(editedItem.value);
      break;
    case 'studentTeam':
      // Import student team data
      break;
    case 'dormSelect':
      await adminUtils.updateDormSelect(editedItem.value)
      break;
    case 'selectTime':
      await adminUtils.updateSelectTime(editedItem.value);
      break;
    case 'building':
      await adminUtils.updateBuildingData(editedItem.value);
      break;
    case 'zoning':
      await adminUtils.updateZoningData(editedItem.value);
      break;
    default:
      break;
  }
  await fetchData();
};

</script>

<template>
  <el-container>
    <SideBar/>
    <el-main :span="22">
      <div>

        <div v-if="setting === 'zoning'">
          <el-button type="primary" @click="newZoningDialogVisible = true">New Zoning</el-button>
        </div>

        <div class="settings-bar" v-if="setting === 'studentAcc' || setting === 'dormSelect'">
          <el-upload type="primary" :http-request="onImport" :show-file-list="false">
            <el-button type="primary">Import</el-button>
          </el-upload>
          <el-button type="primary" @click="onExport">Export</el-button>
        </div>

        <div class="send-notice-wrapper" v-if="setting === 'sendNotice'">
          <h1> 公告发送 </h1>
          <label>标题</label>
          <el-input v-model="sendNoticeData.title"></el-input>
          <label>内容</label>
          <el-input type="textarea" v-model="sendNoticeData.content"></el-input>
          <el-button type="primary" @click="onSendNotice">Send</el-button>
        </div>

        <div class="table" v-if="setting !== 'sendNotice'">
            <el-table :data="adminUtils.getFilterData(data[setting], searchFilter, columns[setting])"
                    style="width: 100%"
                    @expand-change="handleExpandChange"
                    v-loading="loading"
                    :default-sort="{prop: 'id', order: 'ascending'}">
            <el-table-column v-if="setting === 'studentTeam'" type="expand" >
              <el-table :data="selectedTeamMembers" style="width: 100%" class="sub-table">
                <el-table-column prop="id" label="StudentID"></el-table-column>
                <el-table-column prop="name" label="Name"></el-table-column>
                <el-table-column prop="profile" label="Profile"></el-table-column>
              </el-table>
            </el-table-column>
            <el-table-column v-else-if="setting === 'dormSelect'" type="expand" >
              <el-table :data="selectedDormMembers" style="width: 100%" class="sub-table">
                <el-table-column prop="id" label="StudentID"></el-table-column>
                <el-table-column prop="name" label="Name"></el-table-column>
                <el-table-column prop="profile" label="Profile"></el-table-column>
              </el-table>
            </el-table-column>
            <el-table-column v-for="column in columns[setting]"
                             :key="column"
                             :prop="column.prop"
                             :label="column.label"
                             :sortable="column.sortable"
                             :filters="'filter' in column ? column['filter'] : null">
              <template #default="{ row }">
                <span v-if="column.subProp"> {{ row[column.prop][column.subProp] }} </span>
                <span v-else>{{ row[column.prop] }}</span>
              </template>
            </el-table-column>
            <el-table-column align="right">
              <template #header>
                <el-input v-model="searchFilter" size="small" placeholder="Type to search" />
              </template>
              <template #default="{ row }">
                <el-button type="primary" @click="editItem(row)">Edit</el-button>
                <el-button type="danger" @click="onDelete(row)">Delete</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-main>
  </el-container>

  <el-dialog v-model="editDialogVisible" title="Edit" width="30%">
    <el-form :model="editedItem" v-if="setting === 'studentAcc'">
      <el-form-item label="学号">
        <el-input v-model="editedItem.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="editedItem.name"></el-input>
      </el-form-item>
      <el-form-item label="学生性别">
        <el-radio-group v-model="editedItem.sex">
          <el-radio-button label="Male"></el-radio-button>
          <el-radio-button label="Female"></el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="学生类别">
        <el-radio-group v-model="editedItem.type">
          <el-radio-button label="Master"></el-radio-button>
          <el-radio-button label="Doctoral"></el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <el-form :model="editedItem" v-else-if="setting === 'dormSelect'">
      <el-form-item label="房间类型">
        <el-radio-group v-model="editedItem.roomType">
          <el-radio-button label="Single"></el-radio-button>
          <el-radio-button label="Double"></el-radio-button>
          <el-radio-button label="Triple"></el-radio-button>
          <el-radio-button label="Quadruple"></el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <el-form :model="editedItem" v-else-if="setting === 'selectTime'">
      <el-form-item label="组队开始时间">
        <el-input v-model="editedItem.preStartTime" type="datetime-local"></el-input>
      </el-form-item>
      <el-form-item label="组队结束时间">
        <el-input v-model="editedItem.preEndTime" type="datetime-local"></el-input>
      </el-form-item>
      <el-form-item label="选宿舍开始时间">
        <el-input v-model="editedItem.selectStartTime" type="datetime-local"></el-input>
      </el-form-item>
      <el-form-item label="选宿舍结束时间">
        <el-input v-model="editedItem.selectEndTime" type="datetime-local"></el-input>
      </el-form-item>
      <el-form-item label="学生性别">
        <el-radio-group v-model="editedItem.sex">
          <el-radio-button label="Male"></el-radio-button>
          <el-radio-button label="Female"></el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="学生类别">
        <el-radio-group v-model="editedItem.type">
          <el-radio-button label="Master"></el-radio-button>
          <el-radio-button label="Doctoral"></el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <el-form :model="editedItem" v-else-if="setting === 'building'">
      <el-form-item label="楼栋名字">
        <el-input v-model="editedItem.buildingName"></el-input>
      </el-form-item>
      <el-form-item label="分区名字">
        <el-input v-model="editedItem.zoningName"></el-input>
      </el-form-item>
      <el-form-item label="楼栋描述">
        <el-input v-model="editedItem.buildingDescription"></el-input>
      </el-form-item>
    </el-form>

    <el-form :model="editedItem" v-else-if="setting === 'zoning'">
      <el-form-item label="分区名字">
        <el-input v-model="editedItem.zoningName"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="editDialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="saveItem">Save</el-button>
    </span>
  </el-dialog>
  <el-dialog v-model="confirmDialogVisible" title="Confirm" width="30%">
    <span slot="footer" class="dialog-footer">
      <el-button @click="confirmDialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="deleteItem">Confirm</el-button>
    </span>
  </el-dialog>
  <el-dialog v-model="newZoningDialogVisible" title="New Zoning" width="30%">
    <el-form :model="newZoningData">
      <el-form-item label="分区名字">
        <el-input v-model="newZoningData.zoningName"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="newZoningDialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="saveNewZoning(newZoningData)">Save</el-button>
    </span>
  </el-dialog>
</template>


<style scoped>

.settings-bar {
  align-self: flex-end;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.settings-bar button {
  margin: 0 10px;
}

.settings-bar button.active {
  font-weight: bold;
}

.el-aside {
  height: 100vh;
  width: auto;
  background-color: #304156;
}

.filters {
  display: flex;
  flex-direction: row;
}

.filter {
  display: flex;
  flex-direction: row;
  margin: 0 10px;

  .el-input {
    width: 100%;
    margin: 0 10px;
  }
}

.el-container {
  height: 100vh;
  width: 100vw;
  top: 0;
  left: 0;
  position: fixed;
}

.sub-table {
  padding-left: 50px;
}

</style>
