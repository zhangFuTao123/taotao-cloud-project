package com.taotao.cloud.workflow.api.common.util;


/**
 */
public class FilePathUtil {

    private static ConfigValueUtil configValueUtil = SpringContext.getBean(ConfigValueUtil.class);

    /**
     * 通过fileType获取文件夹名称
     *
     * @param fileType 文件类型
     * @return
     */
    public static String getFilePath(String fileType) {
        fileType = XSSEscape.escape(fileType);
        String filePath = null;
        //获取文件保存路径
        switch (fileType.toLowerCase()) {
            //用户头像存储路径
            case FileTypeEnum.USERAVATAR:
                filePath = configValueUtil.getUserAvatarFilePath();
                break;
            //邮件文件存储路径
            case FileTypeEnum.MAIL:
                filePath = configValueUtil.getEmailFilePath();
                break;
            //前端附件文件目录
            case FileTypeEnum.ANNEX:
                filePath = configValueUtil.getWebAnnexFilePath();
                break;
            case FileTypeEnum.ANNEXPIC:
                filePath = configValueUtil.getWebAnnexFilePath();
                break;
            //IM聊天图片+语音存储路径
            case FileTypeEnum.IM:
                filePath = configValueUtil.getImContentFilePath();
                break;
            //临时文件存储路径
            case FileTypeEnum.WORKFLOW:
                filePath = configValueUtil.getTemporaryFilePath();
                break;
            //文档管理存储路径
            case FileTypeEnum.DOCUMENT:
                filePath = configValueUtil.getDocumentFilePath();
                break;
            //数据库备份文件路径
            case FileTypeEnum.DATABACKUP:
                filePath = configValueUtil.getDataBackupFilePath();
                break;
            //临时文件存储路径
            case FileTypeEnum.TEMPORARY:
                filePath = configValueUtil.getTemporaryFilePath();
                break;
            //允许上传文件类型
            case FileTypeEnum.ALLOWUPLOADFILETYPE:
                filePath = configValueUtil.getAllowUploadFileType();
                break;
            //文件在线预览存储pdf
            case FileTypeEnum.DOCUMENTPREVIEWPATH:
                filePath = configValueUtil.getDocumentPreviewPath();
                break;
            //文件模板存储路径
            case FileTypeEnum.TEMPLATEFILE:
                filePath = configValueUtil.getTemplateFilePath();
                break;
            //前端文件目录
            case FileTypeEnum.SERVICEDIRECTORY:
                break;
            //后端文件目录
            case FileTypeEnum.WEBDIRECTORY:
                filePath = configValueUtil.getCodeAreasName();
                break;
            //大屏
            case FileTypeEnum.BIVISUALPATH:
                filePath = configValueUtil.getBiVisualPath();
                break;
            //导出
            case FileTypeEnum.EXPORT:
                filePath = configValueUtil.getTemporaryFilePath();
                break;
            default:
                break;
        }
        return filePath;
    }
}
