import axiosInstance from "@/utils/axios";
import {ref} from 'vue'
import {formatTime} from "@/components/Student/pages/Message/utils";
import axios from "axios";

export async function exportDormSelect() {
    // download the file
    await axiosInstance.get('/teacher/export', {
        responseType: 'blob'
    }).then(response => {
        const blob = new Blob([response.data], {type: 'application/vnd.ms-excel'})
        const downloadElement = document.createElement('a')
        const href = window.URL.createObjectURL(blob)
        downloadElement.href = href
        downloadElement.download = '宿舍选择结果.xlsx'
        document.body.appendChild(downloadElement)
        downloadElement.click()
        document.body.removeChild(downloadElement)
        window.URL.revokeObjectURL(href)
    }).catch(error => {
        console.error(error);
    })
}


export async function deleteStudentAcc(value) {
    await axiosInstance.delete('/teacher/student', {
        params: {
            id: value.id
        }
    }).then(response => {
        console.log(response)
        if (response.status === 200 && response.data.code === 0) {
            alert("删除成功")
        }
        else {
            alert("删除失败")
        }
    }).catch(error => {
        console.error(error);
    })
}

export async function deleteDormSelect(value) {
    await axiosInstance.delete('/teacher/room', {
        params: {
            id: value.id
        }
    }).then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("删除成功")
            }
            else {
                alert("删除失败")
            }
        }).catch(error => {
        console.error(error);
    })
}

export async function deleteSelectTime(value) {
    await axiosInstance.delete('/teacher/schedule', {
        params: {
            id: value.id
        }
    }).then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("删除成功")
            }
            else {
                alert("删除失败")
            }
    }).catch(error => {
        console.error(error);
    })
}

export async function deleteBuilding(value) {
    await axiosInstance.delete('/teacher/building', {
        params: {
            id: value.id
        }
    }).then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("删除成功")
            }
            else {
                alert("删除失败")
            }
        }).catch(error => {
        console.error(error);
    })
}

export async function deleteZoning(value) {
    await axiosInstance.delete('/teacher/zoning', {
        params: {
            id: value.id
        }
    }).then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("删除成功")
            }
            else {
                alert("删除失败")
            }
        }).catch(error => {
        console.error(error);
    })
}

export async function updateStudentAcc(value) {
    await axiosInstance.post('/teacher/student', null, {
        params: {
            id: value.id,
            name: value.name,
            type: value.type,
            sex: value.sex,
        }
    }).then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("修改成功")
            }
            else {
                alert("修改失败")
            }
        }).catch(error => {
        console.error(error);
    });
}

export async function updateDormSelect(value) {
    await axiosInstance.post('/teacher/room', value, {headers: {'Content-Type': 'application/json'}})
        .then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("修改成功")
            }
            else {
                alert("修改失败")
            }
        }).catch(error => {
        console.error(error);
    });
}


export async function updateSelectTime(value) {
    let formattedValue = {
        id: value.id,
        preStartTime: value.preStartTime.replace(" ", "T"),
        preEndTime: value.preEndTime.replace(" ", "T"),
        selectStartTime: value.selectStartTime.replace(" ", "T"),
        selectEndTime: value.selectEndTime.replace(" ", "T"),
        type: value.type,
        sex: value.sex
    }
    await axiosInstance.post('/teacher/schedule', formattedValue)
        .then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("修改成功")
            }
            else {
                alert("修改失败")
            }
        }).catch(error => {
            console.error(error);
    });
}


export async function fetchTeamMembers(id) {
    const teamMembers = ref([])
    await axiosInstance.get('/team/member', {
        params: {
            id: id
        }
    }).then(response => {
            teamMembers.value = response.data.data;
        }
    ).catch(error => {
        console.error(error);
    });
    return teamMembers.value
}

export async function fetchDormMembers(id) {
    const dormMembers = ref([])
    await axiosInstance.get('/room/students'
        , {
            params: {
                roomId: id
            }
        }).then(response => {
            dormMembers.value = response.data.data;
        }
    ).catch(error => {
        console.error(error);
    });
    return dormMembers.value
}

export function importStudentAccounts(file) {
    let formData = new FormData()
    formData.append('file', file)
    return axiosInstance.post('/teacher/stu/studentAcc', formData)
}

export function importDormSelect(file) {
    let formData = new FormData()
    formData.append('file', file)
    return axiosInstance.post('/teacher/dorm/room', formData)
}

export async function fetchStudentAccData() {
    const studentData = ref([])
    await axiosInstance.get('/teacher/stu/studentAcc')
        .then(response => {
            studentData.value = response.data.data;
        })
        .catch(error => {
            console.error(error);
        });
    return studentData.value
}

export async function fetchStudentTeamData() {
    const studentTeamData = ref([])
    await axiosInstance.get('/teacher/stu/teaminfo')
        .then(response => {
            studentTeamData.value = response.data.data;
        })
        .catch(error => {
            console.error(error);
        });
    console.log(studentTeamData.value)
    return studentTeamData.value
}

export async function fetchDormData() {
    const dormData = ref([])
    await axiosInstance.get('/room')
        .then(response => {
            dormData.value = response.data.data;
        })
        .catch(error => {
            console.error(error);
        });
    return dormData.value
}

export async function fetchSelectTimeData() {
    const selectTimeData = ref([])
    await axiosInstance.get('/teacher/schedule')
        .then(response => {
            selectTimeData.value = response.data.data;
            for (let i = 0; i < selectTimeData.value.length; i++) {
                selectTimeData.value[i].preStartTime = formatTime(selectTimeData.value[i].preStartTime)
                selectTimeData.value[i].preEndTime = formatTime(selectTimeData.value[i].preEndTime)
                selectTimeData.value[i].selectStartTime = formatTime(selectTimeData.value[i].selectStartTime)
                selectTimeData.value[i].selectEndTime = formatTime(selectTimeData.value[i].selectEndTime)
            }
        })
        .catch(error => {
            console.error(error);
        });
    return selectTimeData.value
}

export async function fetchBuildingData() {
    const buildingData = ref([])
    await axiosInstance.get('/teacher/building')
        .then(response => {
            buildingData.value = response.data.data;
            console.log("bv", buildingData.value)
        })
        .catch(error => {
            console.error(error);
        });
    return buildingData.value
}
export async function fetchZoningData() {
    const zoningData = ref([])
    await axiosInstance.get('/teacher/zoning')
        .then(response => {
            zoningData.value = response.data.data;
            console.log("zv", zoningData.value);
        })
        .catch(error => {
            console.error(error);
        });
    return zoningData.value
}
export function getFilterData(data, searchFilter, columns) {
    // get the id of columns whose column has 'searchable'
    if (!data)
        return [];
    const searchableColumns = columns.filter(column => column.searchable).map(column => column.prop);
    if (!searchFilter) {
        return data;
    }
    // Filter the data based on the search filter
    return data.filter(item => {
        return searchableColumns.some(column => {
            return String(item[column]).toLowerCase().includes(searchFilter.toLowerCase());
        });
    });
}

export async function updateBuildingData(data) {
    await axiosInstance.post('/teacher/building', data)
        .then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("修改成功")
            }
            else {
                alert("修改失败")
            }
        }).catch(error => {
            console.error(error);
        });
}
export async function updateZoningData(data) {
    await axiosInstance.post('/teacher/zoning', data, {headers: {'Content-Type': 'application/json'}})
        .then(response => {
            console.log(response)
            if (response.status === 200 && response.data.code === 0) {
                alert("修改成功")
            }
            else {
                alert("修改失败")
            }
        }).catch(error => {
        console.error(error);
    });
}

