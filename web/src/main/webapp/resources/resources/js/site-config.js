(function(){
    var site ={
        //本地开发配置
        website:'http://jysj.dsjyw.local.net/', //站点地址
        staticWebsite: 'http://jysj.dsjyw.local.net/resources/', // 前端服务器地址，用来自己测试数据
        puiWebsite: 'http://jysj.dsjyw.local.net/resources/pui/'//组件地址

        //服务器开发配置
        // website:'http://jysj.dev.net/', //站点地址

        // staticWebsite: 'http://jysj.dev.net/resources/', // 前端服务器地址，用来自己测试数据
        // puiWebsite: 'http://jysj.dev.net/resources/pui/'//组件地址

        // 测试服务器配置
        // website:'http://jysjtest.dsjyw.net/', //站点地址
        // staticWebsite: 'http://jysjtest.dsjyw.net/resources/', // 前端服务器地址，用来自己测试数据
        // puiWebsite: 'http://jysjtest.dsjyw.net/resources/pui/'//组件地址

        // 正式服务器配置
        // website:'http://qy.dsjyw.net/', //站点地址
        // staticWebsite: 'http://qy.dsjyw.net/resources/', // 前端服务器地址，用来自己测试数据
        // puiWebsite: 'http://qy.dsjyw.net/resources/pui/'//组件地址
    }
    _pw_env = {
        status: 0, //0-前端调试，1-后端调试, 2-后端部署
        website: site.website,
        staticWebsite: site.staticWebsite,
        puiWebsite: site.puiWebsite,
        tag: '',
        pkgs:[
            {
                name: 'io',
                path: site.staticWebsite + 'resources/js/'
            },
            {
                name: 'widget',
                path: site.staticWebsite + 'resources/js/'

            },
            {
                name: 'module',
                path: site.staticWebsite + 'resources/js/'
            },
            {
                name: 'page',
                path: site.staticWebsite + 'resources/js/'
            }
        ],
        preload: ['sizzle'],//预加载模块
        //加载组件，任何组件加载都会自动加载core组件。
        //对pui各个组件的一个
        modSettings:{
            notifier: {
                top: 100
            },
            dialog:{
                // opacity: 0.1,
                position: 'fixed',
                theme: 'white',
                title: '提示信息',
                themeUrl: site.staticWebsite + 'resources/css/widget/core.css'
            },
            defender:{
                themeUrl: site.staticWebsite + 'resources/css/widget/core.css'
            },
            scroll:{
                cursorborderradius: 0,
                cursorcolor: '#3d3d3d'
            },
            tooltip:{
                position: {
                    my: 'tc',
                    at: 'bc' //options: tl,tc,tr, rt,rc,rb, bl,bc,br,lt,lc,lb
                },
                styles:{
                    uri: site.staticWebsite + 'resources/css/widget/core.css'
                }
            }
        },

        //统一错误信息入口
        msg:{
            0: '网络加载错误'
        },
        //地址信息
        url: {
            admin_page:{
                business_handling:{
                    protocolDetail:{
                        protocolDetailProtocol: site.website + 'admin/protocol/ajax/exist',
                        protocolDetailCheck: site.website + 'admin/protocol/ajax/check'
                    },
                    changeDetail:{
                        changeDetailProtocol: site.website + 'admin/protocol/new/ajax/exist',
                        // 审核
                        changeDetailCheck: site.website + 'admin/protocol/new/ajax/check',
                    },
                    //协议列表 lixingyu
                    protocolList :{
                        newProtocolList :site.website + 'admin/protocol/new/ajax/protocolList/applyForNewProtocol',
                        whereaboutsgoChangeList :site.website + 'admin/protocol/new/ajax/protocolList/whereAboutsGoChange',
                        normalBusinessList :site.website + 'admin/protocol/new/ajax/protocolList/normalBusiness',
                        weipeiBusinessList :site.website + 'admin/protocol/new/ajax/protocolList/directionnalWeipeiBusiness',
                        doctorBusinessList :site.website + 'admin/protocol/new/ajax/protocolList/doctorBusiness',
                        nowInfo: site.website + 'admin/protocol/new/ajax/cache'
                    },

                    //协议变更列表 lixingyu
                    // changeList:{
                    //     showChangeList :site.website + 'admin/change/ajax/listChange',
                    //     nowInfo: site.website + 'admin/change/ajax/cache'
                    // },
                },

                //学生管理
                students_management :{
                    //派遣
                    dispatch :{
                        //详情页 liaoyueyun
                        detail :{
                            DetailCheck: site.website + 'admin/dispatch/ajax/check'
                        },
                        //学生列表页 lixingyu
                        studentList :{
                            showStu: site.website + 'admin/dispatch/ajax/query',
                            nowInfo: site.website + 'admin/dispatch/ajax/cache',
                            stateInfo: site.website + 'admin/dispatch/ajax/check-status',
                            delInfo: site.website + 'admin/status/info/ajax/delete',
                            filter: site.website + 'admin/dispatch/ajax/audit',
                            repulse: site.website + 'admin/dispatch/ajax/repulse'
                        },
                    },
                    //业务预约
                    bussiness : {
                        showProList: site.website + 'admin/protocol/new/ajax/newProtocolList'
                    },
                    //学籍
                    school_roll :{
                        //详情页 liaoyueyun
                        detail :{
                            DetailCheck: site.website + 'admin/status/info/ajax/check'
                        },
                        //学生列表页 lixingyu
                        studentList :{
                            showStu: site.website + 'admin/status/info/ajax/list',
                            nowInfo: site.website + 'admin/status/info/ajax/cache',
                            delInfo: site.website + 'admin/status/info/ajax/delete',
                            filter: site.website + 'admin/status/info/ajax/audit',
                            repulse: site.website + 'admin/status/info/ajax/repulse',
                            deleteAll: site.website + 'admin/status/info/ajax/batch/delete',
                        },
                    },

                    // 历史数据查询
                    historical_data :{
                        dataList :{
                            showStu: site.website + 'admin/history/data/ajax/list',
                            SshowStu: site.website + 'student/history/data/ajax/list'
                        }
                    },

                    //少数民族
                    minority :{
                        //学生列表页 lixingyu
                        schoolList :{
                            showStu: site.website + 'admin/minority/status/info/ajax/list',
                            nowInfo: site.website + 'admin/minority/ajax/cache'
                        },
                        dispatchList :{
                            showStu: site.website + 'admin/minority/dispatch/ajax/list',
                            nowInfo: site.website + 'admin/minority/ajax/cache'
                        },
                    },
                },

            },

            system:{
                user:{
                    lock:site.website+'admin/user/lock',
                    unlock:site.website+'admin/user/unlock',
                    delBatch:site.website+'admin/user/del',
                    addValid:site.website+'admin/user/add/isExist',
                    modValid:site.website+'admin/user/update/isExist'
                },
            },

            module:{
                // 派遣修改页提交 lijin
                dispatch:{
                    submit:{
                        //后端填写地址
                        submitClass: site.website + 'student/dispatch/ajax/check'
                    }
                },
                // 专业级联 qiyuan
                linkage:{
                    majorClass: site.website + 'general/major/big',
                    majorMinorClass: site.website + 'general/major/middle',
                    majorName: site.website + 'general/major/small',
                    linkageMajor: site.website + 'general/major/ajax/all',
                    historicalLinkageMajor: site.website + 'general/major/ajax/history' // 历史数据专业

                },
                //省市县
                getArea:{
                    getArea: site.website + 'general/place/get_all'

                },
                parameterHidden:{
                    parameterHidden: site.staticWebsite + 'mock/parameterHidden.json'
                },
                //专业名称
                majorName:{
                    majorName: site.website + 'general/major/ajax/list'
                }

            }
        },

    }
})()
